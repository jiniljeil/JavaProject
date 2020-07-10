import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class drewFrame extends JFrame {
	public drewFrame() {
		setSize(300,200);
		setLocation(500,350);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.black);
		JLabel lblNewLabel = new JLabel("¹«½ÂºÎ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(90, 42, 106, 51);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("È®ÀÎ");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(90, 112, 97, 23);
		btnNewButton.setBackground(Color.black);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Drew.getDrewFrame().dispose();
			}
			
		});
		
	}
}
