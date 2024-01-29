package com.alura.fiap.infrastructure.gateway;

import com.alura.fiap.infrastructure.feign.client.QRServerAPIGateway;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;

public class FileQrCodeFeignGatewayTest {

    @Mock
    private QRServerAPIGateway qrServerAPIGateway;

    private FileQrCodeFeignGateway fileQrCodeFeignGateway;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fileQrCodeFeignGateway = new FileQrCodeFeignGateway(qrServerAPIGateway);
    }

    @Test
    public void testCreateImageQRCode() {
        String testData = "TestQRCodeData";
        byte[] expectedResult = {0x01, 0x02, 0x03};

        when(qrServerAPIGateway.createImageQRCode(testData)).thenReturn(ResponseEntity.ok(expectedResult));

        byte[] result = fileQrCodeFeignGateway.createImageQRCode(testData);

        assertArrayEquals(expectedResult, result);
        verify(qrServerAPIGateway, times(1)).createImageQRCode(testData);
    }
}
