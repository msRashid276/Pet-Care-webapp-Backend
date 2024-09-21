package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Order;
import com.ecommerce.petCare.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService{

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public PaymentResponse createPaymentLink(Order order) throws Exception{

        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(""+order.getId())
                .setCancelUrl("")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount((long)order.getTotalPrice()*100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("PetCare")
                                        .build())
                                .build()
                        )
                        .build()
                )
                .build();


        Session session = Session.create(params);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPayment_url(session.getUrl());

        return paymentResponse;
    }
}
