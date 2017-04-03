/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yodcclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JOptionPane;
import peerManage.CommonWindow;

/**
 *
 * @author satyam
 */
public class StartingWindow extends javax.swing.JFrame {

    /**
     * Creates new form StartingWindow
     */
    public StartingWindow() {
        initComponents();
    }
    public static int counterPort=10000;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IPTF = new javax.swing.JTextField();
        ipLabel = new javax.swing.JLabel();
        nickTF = new javax.swing.JTextField();
        nickLabel = new javax.swing.JLabel();
        connect = new javax.swing.JButton();
        yoDcLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome to YoDC!");

        IPTF.setText("localhost");

        ipLabel.setText("IP of Hub :");

        nickTF.setText("satyam");

        nickLabel.setText("Username");

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        yoDcLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.jpg"))); // NOI18N
        yoDcLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nickLabel)
                            .addComponent(ipLabel))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nickTF)
                            .addComponent(IPTF, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 59, Short.MAX_VALUE)
                        .addComponent(yoDcLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(connect, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(yoDcLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nickTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nickLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IPTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipLabel))
                .addGap(18, 18, 18)
                .addComponent(connect)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        String nick=nickTF.getText();
        String ip=IPTF.getText();
        DatagramSocket ds;
        while(true){
            try {
                ds=new DatagramSocket(counterPort);
            } catch (SocketException ex) {
                counterPort += 20;
                continue;
            }
            break;
        }
        int inPort=counterPort;
        try{
            Socket sock = new Socket(ip,3000);
            String str=nick;
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            dos.writeUTF(str);
            dos.writeInt(inPort);
            DataInputStream dis=new DataInputStream(sock.getInputStream());
            String rec=dis.readUTF();
            
            if(rec.equalsIgnoreCase("fail"))
                JOptionPane.showMessageDialog(null, "User not available");
            else{
                
                CommonWindow cw = new CommonWindow(nick,ip,ds,inPort);
                cw.setLocationRelativeTo(null);
                cw.setVisible(true);
                this.setVisible(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket s=new Socket(ip,3600);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage());
                        }
                        while(true){
                            try {
                                sleep(100);
                            } catch (InterruptedException ex) {
                                JOptionPane.showMessageDialog(null,ex.getMessage());
                            }
                        }
                    }
                }).start();
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
        // TODO add your handling code here:
    }//GEN-LAST:event_connectActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IPTF;
    private javax.swing.JButton connect;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JLabel nickLabel;
    private javax.swing.JTextField nickTF;
    private javax.swing.JLabel yoDcLabel;
    // End of variables declaration//GEN-END:variables
}