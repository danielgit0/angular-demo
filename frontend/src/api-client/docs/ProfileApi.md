# ProfileApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createProfile**](ProfileApi.md#createprofile) | **POST** /api/v1/profiles | Create a profile |
| [**deleteProfile**](ProfileApi.md#deleteprofile) | **DELETE** /api/v1/profiles/{id} | Delete a profile |
| [**getProfile**](ProfileApi.md#getprofile) | **GET** /api/v1/profiles/{id} | Retrieve a profile by id information |
| [**getProfiles**](ProfileApi.md#getprofiles) | **GET** /api/v1/profiles | Retrieve profiles information |
| [**updateProfile**](ProfileApi.md#updateprofile) | **PUT** /api/v1/profiles/{id} | Update a profile |



## createProfile

> ProfileResponse createProfile(profileCreateRequest)

Create a profile

Create a profile

### Example

```ts
import {
  Configuration,
  ProfileApi,
} from '';
import type { CreateProfileRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new ProfileApi(config);

  const body = {
    // ProfileCreateRequest (optional)
    profileCreateRequest: ...,
  } satisfies CreateProfileRequest;

  try {
    const data = await api.createProfile(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **profileCreateRequest** | [ProfileCreateRequest](ProfileCreateRequest.md) |  | [Optional] |

### Return type

[**ProfileResponse**](ProfileResponse.md)

### Authorization

[oauth application](../README.md#oauth-application)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Created |  -  |
| **401** | Unautorized |  -  |
| **403** | Forbidden |  -  |
| **0** | Error Message |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deleteProfile

> deleteProfile(id)

Delete a profile

Delete a profile

### Example

```ts
import {
  Configuration,
  ProfileApi,
} from '';
import type { DeleteProfileRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new ProfileApi(config);

  const body = {
    // string | The id
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeleteProfileRequest;

  try {
    const data = await api.deleteProfile(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` | The id | [Defaults to `undefined`] |

### Return type

`void` (Empty response body)

### Authorization

[oauth application](../README.md#oauth-application)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | No Content |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | Not Found |  -  |
| **0** | Error Message |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getProfile

> ProfileResponse getProfile(id)

Retrieve a profile by id information

Retrieve a single profile by id

### Example

```ts
import {
  Configuration,
  ProfileApi,
} from '';
import type { GetProfileRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new ProfileApi(config);

  const body = {
    // string | The id
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetProfileRequest;

  try {
    const data = await api.getProfile(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` | The id | [Defaults to `undefined`] |

### Return type

[**ProfileResponse**](ProfileResponse.md)

### Authorization

[oauth application](../README.md#oauth-application)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |
| **401** | Unautorized |  -  |
| **403** | Forbidden |  -  |
| **404** | Not found |  -  |
| **0** | Error Message |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getProfiles

> ProfilesResponse getProfiles(profileType, page, pageSize)

Retrieve profiles information

Retrieve a profile\&#39;s information by providing one of the following query parameters: &#x60;jobs&#x60;, &#x60;provinceId&#x60;, &#x60;cantonId&#x60;, &#x60;districtId&#x60;

### Example

```ts
import {
  Configuration,
  ProfileApi,
} from '';
import type { GetProfilesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new ProfileApi(config);

  const body = {
    // 'EMPLOYER' | 'EMPLOYEE' | Profile type (EMPLOYER or EMPLOYEE) (optional)
    profileType: profileType_example,
    // number | Page number (0-based) (optional)
    page: 56,
    // number | Page size (optional)
    pageSize: 56,
  } satisfies GetProfilesRequest;

  try {
    const data = await api.getProfiles(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **profileType** | `EMPLOYER`, `EMPLOYEE` | Profile type (EMPLOYER or EMPLOYEE) | [Optional] [Defaults to `undefined`] [Enum: EMPLOYER, EMPLOYEE] |
| **page** | `number` | Page number (0-based) | [Optional] [Defaults to `0`] |
| **pageSize** | `number` | Page size | [Optional] [Defaults to `10`] |

### Return type

[**ProfilesResponse**](ProfilesResponse.md)

### Authorization

[oauth application](../README.md#oauth-application)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **0** | Error Message |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updateProfile

> ProfileResponse updateProfile(id, profileUpdateRequest)

Update a profile

Update a profile

### Example

```ts
import {
  Configuration,
  ProfileApi,
} from '';
import type { UpdateProfileRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new ProfileApi(config);

  const body = {
    // string | The id
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // ProfileUpdateRequest (optional)
    profileUpdateRequest: ...,
  } satisfies UpdateProfileRequest;

  try {
    const data = await api.updateProfile(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` | The id | [Defaults to `undefined`] |
| **profileUpdateRequest** | [ProfileUpdateRequest](ProfileUpdateRequest.md) |  | [Optional] |

### Return type

[**ProfileResponse**](ProfileResponse.md)

### Authorization

[oauth application](../README.md#oauth-application)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Ok |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | Not found |  -  |
| **0** | Error Message |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)
