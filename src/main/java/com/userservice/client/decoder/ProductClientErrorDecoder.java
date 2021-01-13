package com.userservice.client.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.dto.ProductErrorDTO;
import com.userservice.exception.CardNotFoundException;
import com.userservice.exception.NotEnoughProductException;
import com.userservice.exception.ProductNotFoundException;
import com.userservice.exception.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductClientErrorDecoder implements ErrorDecoder {

    final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        ProductErrorDTO productErrorDTO = objectMapper.readValue(response.body().asReader(), ProductErrorDTO.class);
        return throwExceptionByErrorCode(productErrorDTO);
    }

    private Exception throwExceptionByErrorCode(ProductErrorDTO productErrorDTO) {
        switch (productErrorDTO.getCode()) {
            case "0001":
                throw new ProductNotFoundException();
            case "0004":
                throw new NotEnoughProductException();
            default:
                throw new InternalError();
        }
    }
}
