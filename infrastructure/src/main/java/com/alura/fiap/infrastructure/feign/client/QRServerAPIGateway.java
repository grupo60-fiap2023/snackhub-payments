package com.alura.fiap.infrastructure.feign.client;

import com.alura.fiap.infrastructure.configuration.FeignConfig;
import org.junit.experimental.categories.Categories;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Categories.ExcludeCategory
@FeignClient(value = "qr", url = "${api.qr-server.create-qr-code.url}", configuration = FeignConfig.class)
public interface QRServerAPIGateway {

    @GetMapping(value = "/", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    ResponseEntity<byte[]> createImageQRCode(@RequestParam("data") String data);
}
