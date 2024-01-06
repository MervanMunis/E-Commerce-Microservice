package com.microservice.PaymentService.repository;

import com.microservice.PaymentService.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(Long orderId);
}
