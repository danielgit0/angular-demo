plugins {
	java
	id("org.springframework.boot") version "4.1.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.diffplug.spotless") version "8.1.0"
	id("checkstyle")
	id("pmd")
	id("org.openapi.generator") version "7.17.0"
	id("com.github.node-gradle.node") version "7.1.0"
}

group = "com.angular"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
}

val jacksonDataBindNullableVersion = "0.2.6"
val keycloakAdminClientVersion = "26.0.9"
val mapStructVersion = "1.6.3"
val springDocOpenApiVersion = "2.8.12"
val swaggerAnnotationsVersion = "2.2.36"

dependencies {
	annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
	implementation("io.swagger.core.v3:swagger-annotations:${swaggerAnnotationsVersion}")
	implementation("org.keycloak:keycloak-admin-client:${keycloakAdminClientVersion}")
	implementation("org.mapstruct:mapstruct:${mapStructVersion}")
	implementation("org.openapitools:jackson-databind-nullable:${jacksonDataBindNullableVersion}")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springDocOpenApiVersion}")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springframework.security:spring-security-oauth2-jose")
	testAnnotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
	testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-client-test")
	testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-resource-server-test")
	testImplementation("org.springframework.boot:spring-boot-starter-security-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

spotless { // pre-commit
	java {
		target("src/**/*.java")
		targetExclude("**/build/**")
		googleJavaFormat("1.33.0")
		trimTrailingWhitespace()
		endWithNewline()
		removeUnusedImports()
	}
}

checkstyle { // pre-commit
	toolVersion = "12.1.2"
	configFile = file("config/checkstyle/checkstyle.xml")
	configProperties = mapOf(
		"org.checkstyle.google.suppressionfilter.config" to
				file("config/checkstyle/checkstyle-suppressions.xml").absolutePath
	)
}

pmd { // pre-commit
	toolVersion = "6.55.0"
}

val openApiInput = "${projectDir}/src/main/resources/static/openapi/openapi.yml"
val openapiOutputDir = "${layout.buildDirectory.asFile.get()}/generated-sources/openapi"

openApiGenerate {
	generatorName.set("spring")
	inputSpec.set(openApiInput)
	outputDir.set(openapiOutputDir)
	apiPackage.set("com.example.generated.api")
	modelPackage.set("com.example.generated.model")
	configOptions.set(
		mapOf(
			"interfaceOnly" to "true",
			"useTags" to "true",
			"useSpringBoot4" to "true",
			"useResponseEntity" to "true",
			"useJakartaEe" to "true",
		)
	)
}

node {
	version.set("24.12.0")
	//npmVersion.set("10.5.0")
	download.set(true)
}

val openApiBundled = "${layout.buildDirectory.asFile.get()}/generated-openapi-bundle/openapi.yml"
tasks.npmInstall {
	inputs.file("${projectDir}/package.json")
}
tasks.register<com.github.gradle.node.npm.task.NpmTask>("redoclyLint") {
	group = "openapi tools"
	description = "Lint OpenAPI spec with Redocly"

	dependsOn(tasks.npmInstall)

	args.set(
		listOf(
			"run",
			"redocly",
			"lint",
			openApiInput
		)
	)

	inputs.file(openApiInput)
}
tasks.register<com.github.gradle.node.npm.task.NpmTask>("redoclyBundle") {
	group = "openapi tools"
	description = "Bundle OpenAPI spec with Redocly"

	dependsOn(tasks.npmInstall, "redoclyLint")

	args.set(
		listOf(
			"exec", "--",
			"redocly",
			"bundle",
			openApiInput,
			"--output",
			openApiBundled,
			"--ext",
			"yml"
		)
	)

	inputs.file(openApiInput)
	outputs.file(openApiBundled)
}
/*tasks.register<Copy>("copyBundledOpenApiToResources") {
	group = "openapi tools"
	dependsOn("redoclyBundle")
	from(openApiBundled)
	into("${layout.buildDirectory.get()}/resources/static")
}*/

sourceSets {
	main {
		java {
			srcDir("$openapiOutputDir/src/main/java")
		}
	}
}

tasks.named("compileJava") {
	dependsOn(
		tasks.named("openApiGenerate"),
		tasks.named("redoclyBundle"),
//		tasks.named("copyBundledOpenApiToResources"),
	)
}

/*
tasks.named("processResources") {
	dependsOn("copyBundledOpenApiToResources")
}
*/
