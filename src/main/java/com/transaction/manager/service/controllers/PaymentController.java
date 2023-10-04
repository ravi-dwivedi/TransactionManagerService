package com.transaction.manager.service.controllers;

import com.transaction.manager.service.service.PaymentService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/transaction-manager/payment")
@Log4j2
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(@NonNull final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{parentId}")
    public ModelAndView getPaymentsByParentId(@PathVariable @NonNull final Long parentId) {
        final ModelAndView modelAndView = new ModelAndView("payment");
        modelAndView.addObject("installments", paymentService.getPaymentsByParentId(parentId));
        modelAndView.addObject("parentId", parentId);
        return modelAndView;
    }
}
