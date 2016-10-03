package cn.stq.learn.bean.validation.extend.mutiValidation;

public class Address {
	
	@ZipCode.List(value={
			@ZipCode(countryCode="123",message="城市编码以123打头"),
			@ZipCode(countryCode="1234",message="城市编码以1234打头")
	})
	private String zipcodes;

	public String getZipcodes() {
		return zipcodes;
	}

	public void setZipcodes(String zipcodes) {
		this.zipcodes = zipcodes;
	}
	
	
}
