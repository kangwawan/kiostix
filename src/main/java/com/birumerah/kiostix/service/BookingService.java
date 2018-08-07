package com.birumerah.kiostix.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.birumerah.kiostix.dto.DataBookingDTO;
import com.birumerah.kiostix.dto.PaymentDetailDTO;
import com.birumerah.kiostix.dto.ResponseBookingDTO;
import com.birumerah.kiostix.dto.TransactionDetailDTO;
import com.birumerah.kiostix.helper.RestExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("bookingService")
public class BookingService {
	private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	private String authKey = "a2lvc3RpeEFQSTAxMDMwNDIwMTg=";
	
	private String BOOKING_URL = "http://devapi.kiostix.com/transaction/booking";
	private String PAY_BOOKING_URL = "http://devapi.kiostix.com/transaction/pay";
	
    @Autowired
    private LoginService loginService;

    @Autowired
    private RestExecutor exec;
    
	public String addBooking(Map paramMap){
		logger.debug(">>call addBooking");

		//Login to get token
		LoginService login = new LoginService();
		Map loginMap = new HashMap();
		loginMap.put("email", "tbpos1@kiostix.com");
		loginMap.put("password", "admin123");
		String token = login.login(loginMap).getData().getToken();

		paramMap.put("token", token);
		
		//RestExecutor exec = new RestExecutor();
		Object bookingBody = null;
		ResponseBookingDTO bookingResponse = null;
		String responseStr = null;
		try {
			bookingResponse = (ResponseBookingDTO) exec.executePOSTwithCasting(BOOKING_URL, paramMap, authKey, ResponseBookingDTO.class.getName());
			//ObjectMapper m = new ObjectMapper();
			//bookingResponse = m.convertValue(bookingBody, ResponseBookingDTO.class);

			List<DataBookingDTO> dataList = bookingResponse.getData();
			DataBookingDTO data = dataList.get(0);
			if(data!=null){
				TransactionDetailDTO transaction = data.getTransaction_detail();
				String orderNo = transaction.getOrder_no();
				
				List<PaymentDetailDTO> paymentList = data.getPayment_detail();
				for(PaymentDetailDTO payment : paymentList){
					if("Cash".equals(payment.getPayment_name())){
						String paymentId = payment.getPayment_id();
						Map paymentMap = new HashMap();
						paymentMap.put("id", paymentId);
						paymentMap.put("token", token); //411111-1111-e484b91d-5bc7-4ae6-af90-30cbdc36ef87
						
						Map prmMap = new HashMap();
						prmMap.put("order_no", orderNo);
						prmMap.put("voucher_code", "");
						prmMap.put("payment", paymentMap);
						
						responseStr = payBooking(prmMap);
						
						break;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(">>> Error : "+e.getMessage());
		}
		return responseStr;
	}	

	public String payBooking(Map paramMap){
		logger.debug(">>call payBooking");
		
//		RestExecutor exec = new RestExecutor();
		return exec.executePOST(PAY_BOOKING_URL, paramMap, authKey);
		
	}
	
//	public static void main(String[] args){
//		BookingService booking = new BookingService();
//		String paramStr = "{\"item\":{\"pw934umzpl\":\"2\"},\"time\":\"pwz377bzpl\",\"name\":\"Test API\",\"email\":\"apidev@kiostix.io\",\"gender\":\"Male\",\"phone\":\"085814174847\",\"dob\":\"1996-03-06\"}";		
//		Gson gson = new Gson();
//		ObjectMapper m = new ObjectMapper();
//		Map paramMap = m.convertValue(gson.toJson(paramStr), Map.class);
//
//		booking.addBooking(paramMap);
//	}
	
}