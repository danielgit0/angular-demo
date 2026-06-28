# CompanyApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createCompany**](CompanyApi.md#createcompany) | **POST** /api/v1/companies | Create a new company |
| [**deleteCompany**](CompanyApi.md#deletecompany) | **DELETE** /api/v1/companies/{id} | Delete a company |
| [**getCompanies**](CompanyApi.md#getcompanies) | **GET** /api/v1/companies | Retrieve all companies |
| [**getCompany**](CompanyApi.md#getcompany) | **GET** /api/v1/companies/{id} | Retrieve a company by id |
| [**updateCompany**](CompanyApi.md#updatecompany) | **PUT** /api/v1/companies/{id} | Update a company |



## createCompany

> CompanyResponse createCompany(companyCreateRequest)

Create a new company

Create a new company

### Example

```ts
import {
  Configuration,
  CompanyApi,
} from '';
import type { CreateCompanyRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new CompanyApi(config);

  const body = {
    // CompanyCreateRequest (optional)
    companyCreateRequest: ...,
  } satisfies CreateCompanyRequest;

  try {
    const data = await api.createCompany(body);
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
| **companyCreateRequest** | [CompanyCreateRequest](CompanyCreateRequest.md) |  | [Optional] |

### Return type

[**CompanyResponse**](CompanyResponse.md)

### Authorization

[oauth application](../README.md#oauth-application)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Created |  -  |
| **400** | Bad Request |  -  |
| **0** | Error Message |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deleteCompany

> deleteCompany(id)

Delete a company

Delete a company

### Example

```ts
import {
  Configuration,
  CompanyApi,
} from '';
import type { DeleteCompanyRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new CompanyApi(config);

  const body = {
    // string | The id
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeleteCompanyRequest;

  try {
    const data = await api.deleteCompany(body);
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


## getCompanies

> CompaniesResponse getCompanies(page, pageSize)

Retrieve all companies

Retrieve all companies

### Example

```ts
import {
  Configuration,
  CompanyApi,
} from '';
import type { GetCompaniesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new CompanyApi(config);

  const body = {
    // number | Page number (0-based) (optional)
    page: 56,
    // number | Page size (optional)
    pageSize: 56,
  } satisfies GetCompaniesRequest;

  try {
    const data = await api.getCompanies(body);
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
| **page** | `number` | Page number (0-based) | [Optional] [Defaults to `0`] |
| **pageSize** | `number` | Page size | [Optional] [Defaults to `10`] |

### Return type

[**CompaniesResponse**](CompaniesResponse.md)

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


## getCompany

> CompanyResponse getCompany(id)

Retrieve a company by id

Retrieve a single company by id

### Example

```ts
import {
  Configuration,
  CompanyApi,
} from '';
import type { GetCompanyRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new CompanyApi(config);

  const body = {
    // string | The id
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetCompanyRequest;

  try {
    const data = await api.getCompany(body);
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

[**CompanyResponse**](CompanyResponse.md)

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
| **404** | Nor found |  -  |
| **0** | Error Message |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updateCompany

> CompanyResponse updateCompany(id, companyUpdateRequest)

Update a company

Update a company

### Example

```ts
import {
  Configuration,
  CompanyApi,
} from '';
import type { UpdateCompanyRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({
    // To configure OAuth2 access token for authorization: oauth application
    accessToken: "YOUR ACCESS TOKEN",
  });
  const api = new CompanyApi(config);

  const body = {
    // string | The id
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // CompanyUpdateRequest (optional)
    companyUpdateRequest: ...,
  } satisfies UpdateCompanyRequest;

  try {
    const data = await api.updateCompany(body);
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
| **companyUpdateRequest** | [CompanyUpdateRequest](CompanyUpdateRequest.md) |  | [Optional] |

### Return type

[**CompanyResponse**](CompanyResponse.md)

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
