/**
 */

package swagger.implementation;

import swagger.MyAPI;
import com.microsoft.rest.ServiceClient;
import com.microsoft.rest.RestClient;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import com.google.common.reflect.TypeToken;
import com.microsoft.rest.RestException;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import java.io.IOException;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * Initializes a new instance of the MyAPI class.
 */
public class MyAPIImpl extends ServiceClient implements MyAPI {
    /**
     * The Retrofit service to perform REST calls.
     */
    private MyAPIService service;

    /**
     * Initializes an instance of MyAPI client.
     */
    public MyAPIImpl() {
        this("http://localhost/");
    }

    /**
     * Initializes an instance of MyAPI client.
     *
     * @param baseUrl the base URL of the host
     */
    public MyAPIImpl(String baseUrl) {
        super(baseUrl);
        initialize();
    }

    /**
     * Initializes an instance of MyAPI client.
     *
     * @param clientBuilder the builder for building an OkHttp client, bundled with user configurations
     * @param restBuilder the builder for building an Retrofit client, bundled with user configurations
     */
    public MyAPIImpl(OkHttpClient.Builder clientBuilder, Retrofit.Builder restBuilder) {
        this("http://localhost/", clientBuilder, restBuilder);
        initialize();
    }

    /**
     * Initializes an instance of MyAPI client.
     *
     * @param baseUrl the base URL of the host
     * @param clientBuilder the builder for building an OkHttp client, bundled with user configurations
     * @param restBuilder the builder for building an Retrofit client, bundled with user configurations
     */
    public MyAPIImpl(String baseUrl, OkHttpClient.Builder clientBuilder, Retrofit.Builder restBuilder) {
        super(baseUrl, clientBuilder, restBuilder);
        initialize();
    }

    /**
     * Initializes an instance of MyAPI client.
     *
     * @param restClient the REST client containing pre-configured settings
     */
    public MyAPIImpl(RestClient restClient) {
        super(restClient);
        initialize();
    }

    private void initialize() {
        initializeService();
    }

    private void initializeService() {
        service = retrofit().create(MyAPIService.class);
    }

    /**
     * The interface defining all the services for MyAPI to be
     * used by Retrofit to perform actually REST calls.
     */
    interface MyAPIService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: .MyAPI apiValuesGet" })
        @GET("api/Values")
        Observable<Response<ResponseBody>> apiValuesGet();

        @Headers({ "Content-Type: application/json-patch+json; charset=utf-8", "x-ms-logging-context: .MyAPI apiValuesPost" })
        @POST("api/Values")
        Observable<Response<ResponseBody>> apiValuesPost(@Body String value);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: .MyAPI apiValuesByIdGet" })
        @GET("api/Values/{id}")
        Observable<Response<ResponseBody>> apiValuesByIdGet(@Path("id") int id);

        @Headers({ "Content-Type: application/json-patch+json; charset=utf-8", "x-ms-logging-context: .MyAPI apiValuesByIdPut" })
        @PUT("api/Values/{id}")
        Observable<Response<ResponseBody>> apiValuesByIdPut(@Path("id") int id, @Body String value);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: .MyAPI apiValuesByIdDelete" })
        @HTTP(path = "api/Values/{id}", method = "DELETE", hasBody = true)
        Observable<Response<ResponseBody>> apiValuesByIdDelete(@Path("id") int id);

    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the List&lt;String&gt; object if successful.
     */
    public List<String> apiValuesGet() {
        return apiValuesGetWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<List<String>> apiValuesGetAsync(final ServiceCallback<List<String>> serviceCallback) {
        return ServiceFuture.fromResponse(apiValuesGetWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;String&gt; object
     */
    public Observable<List<String>> apiValuesGetAsync() {
        return apiValuesGetWithServiceResponseAsync().map(new Func1<ServiceResponse<List<String>>, List<String>>() {
            @Override
            public List<String> call(ServiceResponse<List<String>> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;String&gt; object
     */
    public Observable<ServiceResponse<List<String>>> apiValuesGetWithServiceResponseAsync() {
        return service.apiValuesGet()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<List<String>>>>() {
                @Override
                public Observable<ServiceResponse<List<String>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<List<String>> clientResponse = apiValuesGetDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<List<String>> apiValuesGetDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<List<String>, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<List<String>>() { }.getType())
                .build(response);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void apiValuesPost() {
        apiValuesPostWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> apiValuesPostAsync(final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(apiValuesPostWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> apiValuesPostAsync() {
        return apiValuesPostWithServiceResponseAsync().map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> apiValuesPostWithServiceResponseAsync() {
        final String value = null;
        return service.apiValuesPost(value)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = apiValuesPostDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     *
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void apiValuesPost(String value) {
        apiValuesPostWithServiceResponseAsync(value).toBlocking().single().body();
    }

    /**
     *
     * @param value the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> apiValuesPostAsync(String value, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(apiValuesPostWithServiceResponseAsync(value), serviceCallback);
    }

    /**
     *
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> apiValuesPostAsync(String value) {
        return apiValuesPostWithServiceResponseAsync(value).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> apiValuesPostWithServiceResponseAsync(String value) {
        return service.apiValuesPost(value)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = apiValuesPostDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> apiValuesPostDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the String object if successful.
     */
    public String apiValuesByIdGet(int id) {
        return apiValuesByIdGetWithServiceResponseAsync(id).toBlocking().single().body();
    }

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<String> apiValuesByIdGetAsync(int id, final ServiceCallback<String> serviceCallback) {
        return ServiceFuture.fromResponse(apiValuesByIdGetWithServiceResponseAsync(id), serviceCallback);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the String object
     */
    public Observable<String> apiValuesByIdGetAsync(int id) {
        return apiValuesByIdGetWithServiceResponseAsync(id).map(new Func1<ServiceResponse<String>, String>() {
            @Override
            public String call(ServiceResponse<String> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the String object
     */
    public Observable<ServiceResponse<String>> apiValuesByIdGetWithServiceResponseAsync(int id) {
        return service.apiValuesByIdGet(id)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<String>>>() {
                @Override
                public Observable<ServiceResponse<String>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<String> clientResponse = apiValuesByIdGetDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<String> apiValuesByIdGetDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<String, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<String>() { }.getType())
                .build(response);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void apiValuesByIdPut(int id) {
        apiValuesByIdPutWithServiceResponseAsync(id).toBlocking().single().body();
    }

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> apiValuesByIdPutAsync(int id, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(apiValuesByIdPutWithServiceResponseAsync(id), serviceCallback);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> apiValuesByIdPutAsync(int id) {
        return apiValuesByIdPutWithServiceResponseAsync(id).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> apiValuesByIdPutWithServiceResponseAsync(int id) {
        final String value = null;
        return service.apiValuesByIdPut(id, value)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = apiValuesByIdPutDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     *
     * @param id the int value
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void apiValuesByIdPut(int id, String value) {
        apiValuesByIdPutWithServiceResponseAsync(id, value).toBlocking().single().body();
    }

    /**
     *
     * @param id the int value
     * @param value the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> apiValuesByIdPutAsync(int id, String value, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(apiValuesByIdPutWithServiceResponseAsync(id, value), serviceCallback);
    }

    /**
     *
     * @param id the int value
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> apiValuesByIdPutAsync(int id, String value) {
        return apiValuesByIdPutWithServiceResponseAsync(id, value).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param id the int value
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> apiValuesByIdPutWithServiceResponseAsync(int id, String value) {
        return service.apiValuesByIdPut(id, value)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = apiValuesByIdPutDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> apiValuesByIdPutDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void apiValuesByIdDelete(int id) {
        apiValuesByIdDeleteWithServiceResponseAsync(id).toBlocking().single().body();
    }

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> apiValuesByIdDeleteAsync(int id, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(apiValuesByIdDeleteWithServiceResponseAsync(id), serviceCallback);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> apiValuesByIdDeleteAsync(int id) {
        return apiValuesByIdDeleteWithServiceResponseAsync(id).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> apiValuesByIdDeleteWithServiceResponseAsync(int id) {
        return service.apiValuesByIdDelete(id)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = apiValuesByIdDeleteDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> apiValuesByIdDeleteDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<Void>() { }.getType())
                .build(response);
    }

}
