package uw.httpclient.http;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import uw.task.exception.TaskPartnerException;
import uw.task.exception.MapperException;
import uw.httpclient.util.StringUriTemplateUtil;

import java.io.IOException;
import java.util.Map;

/**
 * Http请求方法抽象实现
 * 
 * @author liliang
 * @since 2017/9/20
 */
public abstract class AbstractHttpInterface implements HttpInterface {

	/**
	 * 自定义请求
	 * 
	 * @param request
	 * @param responseType
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> requestForEntity(Request request, Class<T> responseType) throws TaskPartnerException {
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * 自定义请求
     * @param request
     * @param typeRef
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> requestForEntity(Request request, TypeReference<T> typeRef) throws TaskPartnerException {
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * 自定义请求
	 * 
	 * @param request
	 * @param responseType
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T requestForObject(Request request, Class<T> responseType) throws TaskPartnerException {
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * 自定义请求
     * @param request
     * @param typeRef
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T requestForObject(Request request, TypeReference<T> typeRef) throws TaskPartnerException {
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * 自定义请求
	 * 
	 * @param request
	 * @param responseType
	 * @param httpConfig
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> requestEntity(Request request, Class<T> responseType, HttpConfig httpConfig)
			throws TaskPartnerException {
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * 自定义请求
     * @param request
     * @param typeRef
     * @param httpConfig
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> requestEntity(Request request, TypeReference<T> typeRef,HttpConfig httpConfig)
            throws TaskPartnerException {
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * 自定义请求
	 * 
	 * @param request
	 * @param responseType
	 * @param httpConfig
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T requestForObject(Request request, Class<T> responseType, HttpConfig httpConfig)
			throws TaskPartnerException {
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * 自定义请求
     * @param request
     * @param typeRef
     * @param httpConfig
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T requestForObject(Request request, TypeReference<T> typeRef,HttpConfig httpConfig) throws TaskPartnerException
    {
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> getForEntity(String url, TypeReference<T> typeRef,Object... uriVariables) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T getForObject(String url, TypeReference<T> typeRef,Object... uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> getForEntity(String url, TypeReference<T> typeRef, Map<String, ?> uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T getForObject(String url, TypeReference<T> typeRef, Map<String, ?> uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType, HttpConfig httpConfig,
			Map<String, ?> uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> getForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,
                                        Map<String, ?> uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T getForObject(String url, Class<T> responseType, HttpConfig httpConfig, Map<String, ?> uriVariables)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T getForObject(String url, TypeReference<T> typeRef, HttpConfig httpConfig,Map<String, ?> uriVariables) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType, HttpConfig httpConfig,
			Object... uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> getForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,Object... uriVariables) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T getForObject(String url, Class<T> responseType, HttpConfig httpConfig, Object... uriVariables)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T getForObject(String url, TypeReference<T> typeRef, HttpConfig httpConfig,Object... uriVariables) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @param <T>
	 * @return
	 */
	public <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, Class<T> responseType,
			Object... uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     */
    public <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, TypeReference<T> typeRef,
                                        Object... uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @param <T>
	 * @return
	 */
	public <T> T getForObject(String url, Map<String, String> headers, Class<T> responseType, Object... uriVariables)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     */
    public <T> T getForObject(String url, Map<String, String> headers, TypeReference<T> typeRef,
                       Object... uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, Class<T> responseType,
			HttpConfig httpConfig, Object... uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                                        Object... uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T getForObject(String url, Map<String, String> headers, Class<T> responseType, HttpConfig httpConfig,
			Object... uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T getForObject(String url, Map<String, String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                       Object... uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, Class<T> responseType,
			Map<String, ?> uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, TypeReference<T> typeRef,
                                        Map<String, ?> uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T getForObject(String url, Map<String, String> headers, Class<T> responseType,
			Map<String, ?> uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T getForObject(String url, Map<String, String> headers,TypeReference<T> typeRef,
                       Map<String, ?> uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers, Class<T> responseType,
			HttpConfig httpConfig, Map<String, ?> uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> getForEntity(String url, Map<String, String> headers,TypeReference<T> typeRef,HttpConfig httpConfig,
                                        Map<String, ?> uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * GET
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param uriVariables
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T getForObject(String url, Map<String, String> headers, Class<T> responseType, HttpConfig httpConfig,
			Map<String, ?> uriVariables) throws TaskPartnerException {
		Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * GET
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param uriVariables
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T getForObject(String url, Map<String, String> headers,TypeReference<T> typeRef,HttpConfig httpConfig,
                       Map<String, ?> uriVariables) throws TaskPartnerException {
        Request request = new Request.Builder().url(StringUriTemplateUtil.expand(url, uriVariables))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param responseType
	 * @param requestBody
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, RequestBody requestBody)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(requestBody).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param typeRef
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, RequestBody requestBody)
            throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(requestBody).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param responseType
	 * @param requestBody
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T postForObject(String url, Class<T> responseType, RequestBody requestBody) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(requestBody).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param typeRef
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, TypeReference<T> typeRef, RequestBody requestBody) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(requestBody).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param requestBody
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, HttpConfig httpConfig,
			RequestBody requestBody) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(requestBody).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,
                                         RequestBody requestBody) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(requestBody).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param requestBody
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T postForObject(String url, Class<T> responseType, HttpConfig httpConfig, RequestBody requestBody)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(requestBody).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, TypeReference<T> typeRef, HttpConfig httpConfig,RequestBody requestBody) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(requestBody).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param headers
	 * @param responseType
	 * @param requestBody
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> postForEntity(String url, Map<String, String> headers, Class<T> responseType,
			RequestBody requestBody) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(requestBody).headers(Headers.of(headers)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers,TypeReference<T> typeRef,
                                         RequestBody requestBody) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(requestBody).headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param headers
	 * @param responseType
	 * @param requestBody
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T postForObject(String url, Map<String, String> headers, Class<T> responseType, RequestBody requestBody)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(requestBody).headers(Headers.of(headers)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Map<String,String> headers,TypeReference<T> typeRef,
                        RequestBody requestBody) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(requestBody).headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param headers
	 * @param responseType
	 * @param httpConfig
	 * @param requestBody
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> postForEntity(String url, Map<String, String> headers, Class<T> responseType,
			HttpConfig httpConfig, RequestBody requestBody) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(requestBody).headers(Headers.of(headers)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers,TypeReference<T> typeRef, HttpConfig httpConfig,
                                         RequestBody requestBody) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(requestBody).headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

    /**
	 * POST
	 * 
	 * @param url
	 * @param headers
	 * @param responseType
	 * @param httpConfig
	 * @param requestBody
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T postForObject(String url, Map<String, String> headers, Class<T> responseType, HttpConfig httpConfig,
			RequestBody requestBody) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(requestBody).headers(Headers.of(headers)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param requestBody
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Map<String,String> headers,TypeReference<T> typeRef, HttpConfig httpConfig,
                        RequestBody requestBody) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(requestBody).headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param responseType
	 * @param mediaType
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, String content, MediaType mediaType)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param typeRef
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef,
                                         String content, MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
	public <T> T postForObject(String url, Class<T> responseType, String content, MediaType mediaType)
			throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param typeRef
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, TypeReference<T> typeRef,
                        String content, MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType,
                                         Object content, MediaType mediaType) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(url).post(
                RequestBody.create(mediaType, objectMapper().toString(content))).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
        return handleResponse(null, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param typeRef
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef,
                                         Object content, MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(
                RequestBody.create(mediaType, objectMapper().toString(content))).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Class<T> responseType,
                        Object content, MediaType mediaType) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(url).post(
                RequestBody.create(mediaType, objectMapper().toString(content))).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
        return handleResponse(null, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param typeRef
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, TypeReference<T> typeRef,
                        Object content, MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(
                RequestBody.create(mediaType, objectMapper().toString(content))).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param responseType
	 * @param httpConfig
	 * @param mediaType
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, HttpConfig httpConfig,
			String content, MediaType mediaType) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,
                                         String content, MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param headers
	 * @param responseType
	 * @param mediaType
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T postForObject(String url, Map<String, String> headers, Class<T> responseType, String content,
			MediaType mediaType) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(null, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Map<String,String> headers, TypeReference<T> typeRef,
                        String content,MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param responseType
     * @param httpConfig
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, HttpConfig httpConfig,
                                         Object content, MediaType mediaType) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(url).post(
                RequestBody.create(mediaType, objectMapper().toString(content))).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
        return handleResponse(httpConfig, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param typeRef
     * @param httpConfig
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, TypeReference<T> typeRef, HttpConfig httpConfig,
                                         Object content, MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(
                RequestBody.create(mediaType, objectMapper().toString(content))).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Map<String,String> headers, Class<T> responseType,
                        Object content,MediaType mediaType) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, objectMapper().toString(content)))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
        return handleResponse(null, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Map<String,String> headers, TypeReference<T> typeRef,
                        Object content,MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, objectMapper().toString(content)))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(null, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param headers
	 * @param responseType
	 * @param httpConfig
	 * @param mediaType
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> ResponseWrapper<T> postForEntity(String url, Map<String, String> headers, Class<T> responseType,
			HttpConfig httpConfig, String content, MediaType mediaType) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                                         String content,MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * POST
	 * 
	 * @param url
	 * @param headers
	 * @param responseType
	 * @param httpConfig
	 * @param mediaType
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	public <T> T postForObject(String url, Map<String, String> headers, Class<T> responseType, HttpConfig httpConfig,
			String content, MediaType mediaType) throws TaskPartnerException {
		Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content))
				.headers(Headers.of(headers)).build();
		ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
		return handleResponse(httpConfig, request, extractor);
	}

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Map<String,String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                        String content,MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers, Class<T> responseType,HttpConfig httpConfig,
                                         Object content,MediaType mediaType) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, objectMapper().toString(content)))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), responseType);
        return handleResponse(httpConfig, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, Map<String,String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                                         Object content,MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, objectMapper().toString(content)))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<ResponseWrapper<T>> extractor = responseEntityExtractor(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param headers
     * @param responseType
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Map<String,String> headers, Class<T> responseType,HttpConfig httpConfig,
                        Object content,MediaType mediaType) throws TaskPartnerException
    {
        Request request = new Request.Builder().url(url).post(
                RequestBody.create(mediaType, objectMapper().toString(content)))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), responseType);
        return handleResponse(httpConfig, request, extractor);
    }

    /**
     * POST
     * @param url
     * @param headers
     * @param typeRef
     * @param httpConfig
     * @param mediaType
     * @param <T>
     * @return
     * @throws TaskPartnerException
     */
    public <T> T postForObject(String url, Map<String,String> headers, TypeReference<T> typeRef,HttpConfig httpConfig,
                        Object content,MediaType mediaType) throws TaskPartnerException {
        Request request = new Request.Builder().url(url).post(
                RequestBody.create(mediaType, objectMapper().toString(content)))
                .headers(Headers.of(headers)).build();
        ResponseExtractor<T> extractor = new ObjectExtractor<>(objectMapper(), typeRef);
        return handleResponse(httpConfig, request, extractor);
    }

	/**
	 * responseEntityExtractor
	 * 
	 * @param objectMapper
	 * @param responseType
	 * @param <T>
	 * @return
	 */
    private <T> ResponseExtractor<ResponseWrapper<T>> responseEntityExtractor(ObjectMapper objectMapper,
			Class<T> responseType) {
		return new EntityExtractor<>(objectMapper, responseType);
	}

    /**
     * responseEntityExtractor
     * @param objectMapper
     * @param typeRef
     * @param <T>
     * @return
     */
    private <T> ResponseExtractor<ResponseWrapper<T>> responseEntityExtractor(ObjectMapper objectMapper,
                                                                                TypeReference<T> typeRef) {
        return new EntityExtractor<>(objectMapper, typeRef);
    }

	/**
	 * handleResponse
	 * 
	 * @param request
	 * @param extractor
	 * @param <T>
	 * @return
	 * @throws TaskPartnerException
	 */
	private <T> T handleResponse(HttpConfig httpConfig, final Request request, final ResponseExtractor<T> extractor)
			throws TaskPartnerException {
		if (httpConfig != null)
			httpHelper(httpConfig);
		Response response = null;
		try {
			response = httpHelper().execute(request);
		} catch (IOException e) {
			throw new TaskPartnerException(e.getMessage(), e);
		}
		if (response.isSuccessful()) {
			try {
				return extractor.extractData(request, response,callBack());
			} catch (IOException t) {
				throw new TaskPartnerException("返回结果失败: ", t);
			} catch (MapperException t) {
				throw new TaskPartnerException(String.format("转换失败: %s", t.getMessage()), t);
			} catch (Throwable t) {
				throw new TaskPartnerException(t);
			}
		}
		String resp = "";
		try {
		    resp = response.body().string();
        } catch (IOException e){
        }
        throw new TaskPartnerException("HTTP CODE: "+response.code()+",RESPONSE: "+resp);
	}
}
