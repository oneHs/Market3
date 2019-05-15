package com.smbms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.smbms.pojo.Provider;

public interface ProviderMapper {
	/**
	 * 查询Provider表中所有数据
	 * 
	 * @return
	 */
	@Select("select * from smbms_provider ")
	List<Provider> getProviderAll();

	/**
	 * 查询供应商name
	 * 
	 * @param id
	 * @return
	 */
	@Select("select proName from smbms_provider where id=#{billId}")
	Provider getProviderById(@Param("billId") Integer id);
	/**
	 * 添加供应商信息
	 * @param provider
	 * @return
	 */
	@Insert("insert into "
			+ "smbms_provider"
			+ " (proCode, proName,proDesc,proContact,proPhone,proAddress,proFax,createdBy,creationDate) "
			+ "values"
			+ "( #{provider.proCode},#{provider.proName},#{provider.proDesc},#{provider.proContact},#{provider.proPhone},#{provider.proAddress},#{provider.proFax},#{provider.createdBy},#{provider.creationDate})")
	int providerAdd(@Param("provider") Provider provider);
	/**
	 * 动态查询
	 * @param queryProCode
	 * @param queryProName
	 * @return
	 */
	@Select("<script>"
			+ "select * from smbms_provider"
			+ "<where>"
			+ "<if test=' proCode != null '>"
			+ "and proCode like concat('%',#{proCode},'%')"
			+ "</if>"
			+ "<if test=' proName != null ' > "
			+ "and proName like concat('%',#{proName},'%') "
			+ "</if>"
			+ "</where>"
			+ "</script>")
	List<Provider> getProviderByCN(@Param("proCode")String proCode,@Param("proName")String proName);
}
