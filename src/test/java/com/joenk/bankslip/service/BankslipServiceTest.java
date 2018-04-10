package com.joenk.bankslip.service;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joenk.bankslip.BankslipApplication;
import com.joenk.bankslip.component.BankslipValidator;
import com.joenk.bankslip.entity.Bankslip;
import com.joenk.bankslip.enums.Status;
import com.joenk.bankslip.model.BankslipDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BankslipApplication.class)
public class BankslipServiceTest {

	@InjectMocks
	@Resource
	private BankslipValidator bankslipValidator;

	@Configuration
	static class BankslipServiceTestContextConfiguration {
		@Bean
		public BankslipService bankslipService() {
			return new BankslipService();
		}
		/*@Bean
		public BankslipRepository bankslipRepository() {
			return Mockito.mock(BankslipRepository.class);
		}*/
	}

	//We Autowired the AccountService bean so that it is injected from the configuration
	@Autowired
	private BankslipService bankslipService;

	/*@Autowired
	private BankslipRepository bankslipRepository;*/

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		final Bankslip bankslip = new Bankslip();
		bankslip.setId("771fa05e-2884-4a3c-b4ed-536689e61b46");
		bankslip.setDueDate(bankslipValidator.getDueDate("2018-01-01", null));
		bankslip.setStatus(Status.PENDING);
		bankslip.setTotalInCents(100);
		bankslip.setCustomer("TESTE");
		//Mockito.when(bankslipRepository.findOne("771fa05e-2884-4a3c-b4ed-536689e61b46")).thenReturn(bankslip);
	}

	@Test
	public void createTest(){
		final BankslipDTO bankslipDTO = new BankslipDTO();
		bankslipDTO.setId("771fa05e-2884-4a3c-b4ed-536689e61b46");
		bankslipDTO.setDueDate("2018-01-01");
		bankslipDTO.setStatus("PENDING");
		bankslipDTO.setTotalInCents("100");
		bankslipDTO.setCustomer("TESTE");
		bankslipService.create(bankslipDTO);
	}

}
