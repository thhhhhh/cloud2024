package com.ceesing.cloud.apis;

import com.ceesing.cloud.entities.PayDTO;
import com.ceesing.cloud.resp.ResultData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@FeignClient(name = "cloud-payment-service")
@FeignClient(name = "cloud-gateway")
public interface PayFeignApi {
    @PostMapping(value = "/pay/add")
    public ResultData<String> addPay(@RequestBody PayDTO payDTO);

    @PostMapping(value = "/pay/delete")
    public ResultData<String> deletePay(@RequestBody PayDTO payDTO);

    @PostMapping(value = "/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO);

    @GetMapping(value = "/pay/get/{id}")
    public ResultData<PayDTO> getPayById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/getAll")
    public ResultData<List<PayDTO>> getAllPay();

    @GetMapping(value = "/pay/get/info")
    public ResultData<String> getInfoByConsul();

    @GetMapping(value = "/pay/circuit/get/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/circuit/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/circuit/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData<PayDTO> getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();

    @GetMapping(value = "/pay/gateway/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request);
}
