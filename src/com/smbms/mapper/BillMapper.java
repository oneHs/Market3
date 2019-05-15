package com.smbms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.smbms.pojo.Bill;

public interface BillMapper {
	/**
	 * 执行查询所有数据操作
	 * 
	 * @return 返回所有对象的List数据集合，List长度为0，表示无数据
	 * 
	 * 
	 */
	@Select("select * from smbms_bill")
	List <Bill> getBillAll();

	/**
	 * 动态sql查询商品名称 供应商名称 和是否付款
	 * 
	 * @return
	 */
	@Select("<script>" 
			+ "select * from smbms_bill" 
			+ "<where>"
			+ "<if test='productName!=null'>"
			+ "and productName like concat('%',#{productName},'%')" 
			+ "</if>"
			+ "<if test='providerId!=0'>" 
			+ "and providerId=#{providerId}"
			+ "</if>" + "<if test='isPayment!=0'>"
			+ "and isPayment=#{isPayment}" + 
			"</if>"
			+ "</where>" + 
			"</script>")
	List<Bill> getBillByPPI(@Param("productName") String productName,
			@Param("providerId") int providerId,
			@Param("isPayment") int isPayment);

	/**
	 * 根据Bill获取BillId
	 * 
	 * @param billId
	 * @return
	 */
	@Select("select * from smbms_bill where id=#{billId}")
	Bill getBillById(@Param("billId") int billId);

	/**
	 * 向bill表中 插入数据
	 * 
	 * @return
	 */

	@Insert("insert into smbms_bill(billCode,productName,productDesc,productUnit,productCount,totalPrice,providerId,isPayment,createdBy,creationDate) value( #{bill.billCode},#{bill.productName},#{bill.productDesc},#{bill.productUnit},#{bill.productCount},#{bill.totalPrice},#{bill.providerId},#{bill.isPayment},#{bill.createdBy},#{bill.creationDate} )")
	int insBill(@Param("bill") Bill bill);

	/**
	 * 修改Bill表中的数据
	 * 
	 * @param bill
	 * @return
	 */
	/*
	 * @Update(
	 * "update smbms_bill set productName=#{bill.productName},productDesc=#{bill.productDesc},"
	 * + "productUnit=#{bill.productUnit},productCount=#{bill.productCount}," +
	 * "totalPrice=#{bill.totalPrice},isPayment=#{bill.isPayment},providerId=#{bill.providerId},"
	 * +
	 * "modifyBy=#{bill.modifyBy},modifyDate=#{bill.modifyDate} where id = #{bill.id}"
	 * )
	 */
	@Update("update smbms_bill set productName=#{bill.productName},productDesc=#{bill.productDesc},productUnit=#{bill.productUnit},productCount=#{bill.productCount},totalPrice=#{bill.totalPrice},isPayment=#{bill.isPayment},providerId=#{bill.providerId},modifyBy=#{bill.modifyBy},modifyDate=#{bill.modifyDate} where id = #{bill.id}")
	int updBillById(@Param("bill") Bill bill);
	
	/**
	 * 根据ID删除bill表中的数据
	 * @param id
	 * @return
	 */
	@Delete("delete  from smbms_bill where id=#{id}")
	boolean billDelById(int id);
}
