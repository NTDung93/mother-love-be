package com.motherlove.services;

import com.motherlove.models.payload.dto.CategoryDto;
import com.motherlove.models.payload.dto.PaymentHistoryDto;
import com.motherlove.models.payload.responseModel.CategoryResponse;
import com.motherlove.models.payload.responseModel.CustomPaymentHistoryResponse;
import com.motherlove.models.payload.responseModel.PaymentHistoryResponse;

public interface PaymentHistoryService {
    CustomPaymentHistoryResponse addPaymentHistory (PaymentHistoryDto paymentHistoryDto);
    CustomPaymentHistoryResponse getPaymentHistory(Long id);
    PaymentHistoryResponse getAllPaymentHistories(int pageNo, int pageSize, String sortBy, String sortDir);
    CustomPaymentHistoryResponse updatePaymentHistory(PaymentHistoryDto paymentHistoryDto, long paymentHistoryId);
    void deletePaymentHistory(long id);
}
