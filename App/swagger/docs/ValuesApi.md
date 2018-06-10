# ValuesApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiStatusByIdGet**](ValuesApi.md#apiStatusByIdGet) | **GET** /api/status/{id} | 
[**apiStatusPost**](ValuesApi.md#apiStatusPost) | **POST** /api/status | 


<a name="apiStatusByIdGet"></a>
# **apiStatusByIdGet**
> List&lt;Status&gt; apiStatusByIdGet(id)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ValuesApi;


ValuesApi apiInstance = new ValuesApi();
String id = "id_example"; // String | 
try {
    List<Status> result = apiInstance.apiStatusByIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ValuesApi#apiStatusByIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**List&lt;Status&gt;**](Status.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiStatusPost"></a>
# **apiStatusPost**
> apiStatusPost(value)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ValuesApi;


ValuesApi apiInstance = new ValuesApi();
Status value = new Status(); // Status | 
try {
    apiInstance.apiStatusPost(value);
} catch (ApiException e) {
    System.err.println("Exception when calling ValuesApi#apiStatusPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **value** | [**Status**](Status.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: Not defined

