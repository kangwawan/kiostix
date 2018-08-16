package com.birumerah.kiostix.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.birumerah.kiostix.dto.DataBookingDTO;
import com.birumerah.kiostix.dto.PaymentDetailDTO;
import com.birumerah.kiostix.dto.ResponseBookingDTO;
import com.birumerah.kiostix.dto.TransactionDetailDTO;
import com.birumerah.kiostix.helper.RestExecutor;
import com.birumerah.kiostix.mapper.BookingMapper;
import com.birumerah.kiostix.model.Bookings;
import com.birumerah.kiostix.model.Redeems;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("bookingService")
public class BookingService {
	private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	//@Value("${kiostix.authkey}")
	private String authKey  = "a2lvc3RpeEFQSTAxMDMwNDIwMTg=";
	
//	@Value("${kiostix.hostname}")
	private String HOSTNAME = "http://devapi.kiostix.com";

//	@Value("${kiostix.transaction.booking}")
	private String BOOKING_URL = "http://devapi.kiostix.com/transaction/booking";

//	@Value("${kiostix.transaction.pay}")
	private String PAY_BOOKING_URL = "http://devapi.kiostix.com/transaction/pay";

//	@Value("${kiostix.login.email}")
	private String EMAIL_LOGIN = "Merahbiru@kiostix.com";

//	@Value("${kiostix.login.password}")
	private String PASSWORD_LOGIN = "asdasd";
	
    @Autowired
    private LoginService loginService;

    @Autowired
    private RestExecutor exec;

	@Autowired
	private BookingMapper bookingMapper;

	private Map<String,Object> resultMap = null;
	
	public String addBooking(Map paramMap){
		logger.debug(">>call addBooking");

		Object bookingBody = null;
		ResponseBookingDTO bookingResponse = null;
		String responseStr = null;
		String token = null;
		boolean isReachable = false;
		Bookings booking = null;
		Map<String,Object> bookingParamMap = new HashMap<String,Object>(); 
		
		//Login to get token
		LoginService login = new LoginService();
		Map loginMap = new HashMap();
		
		try {
			ObjectMapper converter = new ObjectMapper();
			booking = converter.convertValue(paramMap, Bookings.class);
			
//			loginMap.put("email", "tbpos1@kiostix.com");
//			loginMap.put("password", "admin123");
			loginMap.put("email", EMAIL_LOGIN);
			loginMap.put("password", PASSWORD_LOGIN);
			token = login.login(loginMap).getData().getToken();

			paramMap.put("token", token);
			//begin: 20180816 to apply change of json param on kiostix 
			bookingParamMap = paramMap;
			Map<String,Integer> item = (Map<String,Integer>) paramMap.get("item");
			paramMap.remove("item");
			paramMap.remove("photo1");
			paramMap.remove("photo2");
			paramMap.remove("amount");
			paramMap.remove("paymentType");
			paramMap.remove("notes1");
			paramMap.remove("notes2");
			paramMap.remove("notes3");

			Map<String,Object> newItemMp = new HashMap<String,Object>();
			if(item != null){
				Iterator iter = item.keySet().iterator();
			    while (iter.hasNext()) {
			    	String key = (String) iter.next();
					newItemMp.put("id", key);
					newItemMp.put("qty", item.get(key));
				}
			}
			paramMap.put("item",newItemMp);
			//logger.debug(">>> json param to kiostix : "+converter.readValue(bookingParamMap, String.class));
			//end
			
			bookingResponse = (ResponseBookingDTO) exec.executePOSTwithCasting(BOOKING_URL, paramMap, authKey, ResponseBookingDTO.class.getName());
			//bookingResponse = (ResponseBookingDTO) exec.executePOSTwithCasting(BOOKING_URL, bookingParamMap, authKey, ResponseBookingDTO.class.getName());

			List<DataBookingDTO> dataList = bookingResponse.getData();
			DataBookingDTO data = dataList.get(0);
			if(data!=null){
				TransactionDetailDTO transaction = data.getTransaction_detail();
				String orderNo = transaction.getOrder_no();
				
				booking.setOrderNo(orderNo);
				
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
			
			booking.setOnline(true);
			booking.setToken(token);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			booking.setOnline(false);
			logger.debug(">>> Error-UnknownHostException : "+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			booking.setOnline(false);
			logger.debug(">>> Error-IOException : "+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(">>> Error-Exception : "+e.getMessage());
		} finally{
			if(booking!=null){
				bookingMapper.insertBooking(booking);
			}
		}
		return responseStr;
	}	

	public String payBooking(Map paramMap){
		logger.debug(">>call payBooking");
		
//		RestExecutor exec = new RestExecutor();
		return exec.executePOST(PAY_BOOKING_URL, paramMap, authKey);
		
	}
	
	public Map<String,Object> saveVoucher(Map paramMap){
		logger.debug(">>call saveVoucher");
		resultMap = new HashMap<String,Object>();

		Object bookingBody = null;
		ResponseBookingDTO bookingResponse = null;
		String responseStr = null;
		String token = null;
		boolean isReachable = false;
		Redeems redeem = null;
				
		try {
			ObjectMapper converter = new ObjectMapper();
			redeem = converter.convertValue(paramMap, Redeems.class);
			
			bookingMapper.insertVoucher(redeem);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("error : "+e.getMessage());
			resultMap.put("status", 500);
			resultMap.put("message", e.getMessage());
		}
		if(!resultMap.containsKey("error")){
			resultMap.put("status", 200);
			resultMap.put("message", "Save voucher is success!");
		}
		return resultMap;
	}	
	
//	public static void main(String[] args){
//		BookingService booking = new BookingService();
//		String paramStr = "{\"item\":{\"pw934umzpl\":\"2\"},\"time\":\"pwz377bzpl\",\"name\":\"Test API\",\"email\":\"apidev@kiostix.io\",\"gender\":\"Male\",\"phone\":\"085814174847\",\"dob\":\"1996-03-06\"}";		
//		Gson gson = new Gson();
//		ObjectMapper m = new ObjectMapper();
//		Map paramMap = m.convertValue(gson.toJson(paramStr), Map.class);
//
//		booking.addBooking(paramMap);
		//logger.debug(">>> isReachable : "+HostStatusChecker.isHostReachable("devapi.kiostix.com"));

//	}
	
}
