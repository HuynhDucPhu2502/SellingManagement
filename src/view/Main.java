package view;

import model.accounts.Account;
import model.accounts.AccountDAO;
import view.base.AuthFrame;
import view.base.MainFrame;

@SuppressWarnings("unused")
public class Main {
	public static void main(String[] args) {
		
//		Auto Login
//		Account account = AccountDAO.getLogin("nguyenxuanlo", "123456");
//		new MainFrame(account);
		
		
		
//		Authentication require
		new AuthFrame();
	}
}


// Members of Group 9 - DHKTPM18BTT - Lap Trinh Huong Su Kien Java
// Huynh Duc Phu
// Dang Nguyen Tien Phat
// Nguyen Tran Long

// Username: nguyenxuanlo
// Password 123456