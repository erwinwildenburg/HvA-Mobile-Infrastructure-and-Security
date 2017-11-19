/**
 */

package swagger;

import com.microsoft.rest.RestException;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import java.io.IOException;
import java.util.List;
import rx.Observable;
import com.microsoft.rest.RestClient;

/**
 * The interface for MyAPI class.
 */
public interface MyAPI {
    /**
     * Gets the REST client.
     *
     * @return the {@link RestClient} object.
    */
    RestClient restClient();

    /**
     * The default base URL.
     */
    String DEFAULT_BASE_URL = "http://localhost/";

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the List&lt;String&gt; object if successful.
     */
    List<String> apiValuesGet();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<List<String>> apiValuesGetAsync(final ServiceCallback<List<String>> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;String&gt; object
     */
    Observable<List<String>> apiValuesGetAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;String&gt; object
     */
    Observable<ServiceResponse<List<String>>> apiValuesGetWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    void apiValuesPost();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Void> apiValuesPostAsync(final ServiceCallback<Void> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<Void> apiValuesPostAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<ServiceResponse<Void>> apiValuesPostWithServiceResponseAsync();
    /**
     *
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    void apiValuesPost(String value);

    /**
     *
     * @param value the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Void> apiValuesPostAsync(String value, final ServiceCallback<Void> serviceCallback);

    /**
     *
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<Void> apiValuesPostAsync(String value);

    /**
     *
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<ServiceResponse<Void>> apiValuesPostWithServiceResponseAsync(String value);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the String object if successful.
     */
    String apiValuesByIdGet(int id);

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<String> apiValuesByIdGetAsync(int id, final ServiceCallback<String> serviceCallback);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the String object
     */
    Observable<String> apiValuesByIdGetAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the String object
     */
    Observable<ServiceResponse<String>> apiValuesByIdGetWithServiceResponseAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    void apiValuesByIdPut(int id);

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Void> apiValuesByIdPutAsync(int id, final ServiceCallback<Void> serviceCallback);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<Void> apiValuesByIdPutAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<ServiceResponse<Void>> apiValuesByIdPutWithServiceResponseAsync(int id);
    /**
     *
     * @param id the int value
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    void apiValuesByIdPut(int id, String value);

    /**
     *
     * @param id the int value
     * @param value the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Void> apiValuesByIdPutAsync(int id, String value, final ServiceCallback<Void> serviceCallback);

    /**
     *
     * @param id the int value
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<Void> apiValuesByIdPutAsync(int id, String value);

    /**
     *
     * @param id the int value
     * @param value the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<ServiceResponse<Void>> apiValuesByIdPutWithServiceResponseAsync(int id, String value);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    void apiValuesByIdDelete(int id);

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Void> apiValuesByIdDeleteAsync(int id, final ServiceCallback<Void> serviceCallback);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<Void> apiValuesByIdDeleteAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    Observable<ServiceResponse<Void>> apiValuesByIdDeleteWithServiceResponseAsync(int id);

}
