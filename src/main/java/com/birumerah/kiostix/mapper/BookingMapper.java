package com.birumerah.kiostix.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.SelectKey;

import com.birumerah.kiostix.model.Bookings;
import com.birumerah.kiostix.model.Redeems;

@Mapper
public interface BookingMapper {

	@Insert("INSERT INTO kiostix_booking(ID,TOKEN,ITEM_NO,ITEM_QTY,TIME,NAME,EMAIL,GENDER,PHONE,DOB,PAYMENT_TYPE,AMOUNT,PHOTO1,PHOTO2,ORDER_NO,NOTES1,NOTES2,NOTES3,IS_ONLINE,CREATED_DATE) VALUES (null,#{token},#{itemNo},#{itemQty},#{time},#{name},#{email},#{gender},#{phone},#{dob},#{paymentType},#{amount},#{photo1},#{photo2},#{orderNo},#{notes1},#{notes2},#{notes3},#{online},now())")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Integer insertBooking(Bookings booking);

	@Insert("INSERT INTO kiostix_redeem(ID,TOKEN,ITEM_NO,ITEM_QTY,TIME,NAME,EMAIL,GENDER,PHONE,DOB,PAYMENT_TYPE,AMOUNT,PHOTO1,PHOTO2,ORDER_NO,NOTES1,NOTES2,NOTES3,IS_ONLINE,CREATED_DATE) VALUES (null,#{token},#{itemNo},#{itemQty},#{time},#{name},#{email},#{gender},#{phone},#{dob},#{paymentType},#{amount},#{photo1},#{photo2},#{orderNo},#{notes1},#{notes2},#{notes3},#{online},now())")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Integer insertVoucher(Redeems redeem);
	
}
