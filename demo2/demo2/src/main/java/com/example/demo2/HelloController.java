package com.example.demo2;


import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class HelloController {

    @FXML
    private Pane presettingspane,settingspane,authpane1,yauth,errorPane,helloPane,emailPane,completePane;

    @FXML
    private ImageView whiteauth,change,whites,ymauth,background,startbutton,whitesettings,usermint,mintsettings,theme,qaa,loginword,passwordword,errorbar,tryagainbutton,emailButton,emailText,completeText,completeButton;
    @FXML
    private PasswordField passtext;
    @FXML
    private TextField logintext;
    boolean vkToY = false;
    boolean wait = false;
    int them =0;
    @FXML
    protected void onCloseCompleteClick(){
        completePane.setVisible(false);
        completePane.setDisable(true);
    }
    @FXML
    protected void onChangeThemeClick() throws IOException {

        if(them==2){
            them=0;
        }else{
            them+=1;
        }
        if(them==1){
            presettingspane.setVisible(false);
            settingspane.setVisible(false);
            background.setImage(new Image(HelloController.class.getResource("blue/mintthemeback.png").openStream()));
            startbutton.setImage(new Image(HelloController.class.getResource("blue/Layer1.png").openStream()));
            whiteauth.setImage(new Image(HelloController.class.getResource("blue/авифв.png").openStream()));
            whitesettings.setImage(new Image(HelloController.class.getResource("blue/settingsbutton2MINT.png").openStream()));
            usermint.setImage(new Image(HelloController.class.getResource("blue/Ellipse7.png").openStream()));
            usermint.setLayoutX(17);
            usermint.setLayoutY(12);
            mintsettings.setImage(new Image(HelloController.class.getResource("blue/settingsMINT.png").openStream()));
            mintsettings.setLayoutX(335);
            mintsettings.setLayoutY(0);
            theme.setImage(new Image(HelloController.class.getResource("blue/еруьуигеещт.png").openStream()));
            qaa.setImage(new Image(HelloController.class.getResource("blue/асфвафвав.png").openStream()));
            loginword.setImage(new Image(HelloController.class.getResource("blue/loginWORD.png").openStream()));
            passwordword.setImage(new Image(HelloController.class.getResource("blue/PASSWORD.png").openStream()));
            errorbar.setImage(new Image(HelloController.class.getResource("blue/errorbarMINT .png").openStream()));
            tryagainbutton.setImage(new Image(HelloController.class.getResource("blue/tryAgain.png").openStream()));
            emailButton.setImage(new Image(HelloController.class.getResource("blue/greatButton.png").openStream()));
            emailText.setImage(new Image(HelloController.class.getResource("blue/mailCopyed.png").openStream()));
            completeText.setImage(new Image(HelloController.class.getResource("blue/completeBar.png").openStream()));
            completeButton.setImage(new Image(HelloController.class.getResource("blue/greatButton.png").openStream()));


        }else if(them==2){
            presettingspane.setVisible(false);
            settingspane.setVisible(false);
            background.setImage(new Image(HelloController.class.getResource("peach/mintthemeback.png").openStream()));
            startbutton.setImage(new Image(HelloController.class.getResource("peach/Layer1.png").openStream()));
            whiteauth.setImage(new Image(HelloController.class.getResource("peach/авифв.png").openStream()));
            whitesettings.setImage(new Image(HelloController.class.getResource("peach/settingsbutton2MINT.png").openStream()));
            usermint.setImage(new Image(HelloController.class.getResource("peach/Ellipse7.png").openStream()));
            usermint.setLayoutX(17);
            usermint.setLayoutY(12);
            mintsettings.setImage(new Image(HelloController.class.getResource("peach/settingsMINT.png").openStream()));
            mintsettings.setLayoutX(335);
            mintsettings.setLayoutY(0);
            theme.setImage(new Image(HelloController.class.getResource("peach/еруьуигеещт.png").openStream()));
            qaa.setImage(new Image(HelloController.class.getResource("peach/асфвафвав.png").openStream()));
            loginword.setImage(new Image(HelloController.class.getResource("peach/loginWORD.png").openStream()));
            passwordword.setImage(new Image(HelloController.class.getResource("peach/PASSWORD.png").openStream()));
            errorbar.setImage(new Image(HelloController.class.getResource("peach/errorbarMINT .png").openStream()));
            tryagainbutton.setImage(new Image(HelloController.class.getResource("peach/tryAgain.png").openStream()));
            emailButton.setImage(new Image(HelloController.class.getResource("peach/greatButton.png").openStream()));
            emailText.setImage(new Image(HelloController.class.getResource("peach/mailCopyed.png").openStream()));
            completeText.setImage(new Image(HelloController.class.getResource("peach/completeBar.png").openStream()));
            completeButton.setImage(new Image(HelloController.class.getResource("peach/greatButton.png").openStream()));

        }else if(them==0){
            presettingspane.setVisible(false);
            settingspane.setVisible(false);
            background.setImage(new Image(HelloController.class.getResource("mintthemeback.png").openStream()));
            startbutton.setImage(new Image(HelloController.class.getResource("Layer1.png").openStream()));
            whiteauth.setImage(new Image(HelloController.class.getResource("авифв.png").openStream()));
            whitesettings.setImage(new Image(HelloController.class.getResource("settingsbutton2MINT.png").openStream()));
            usermint.setImage(new Image(HelloController.class.getResource("Ellipse7.png").openStream()));

            mintsettings.setImage(new Image(HelloController.class.getResource("settingsMINT.png").openStream()));
            mintsettings.setLayoutX(336);
            mintsettings.setLayoutY(6);
            theme.setImage(new Image(HelloController.class.getResource("еруьуигеещт.png").openStream()));
            qaa.setImage(new Image(HelloController.class.getResource("асфвафвав.png").openStream()));
            loginword.setImage(new Image(HelloController.class.getResource("loginWORD.png").openStream()));
            passwordword.setImage(new Image(HelloController.class.getResource("PASSWORD.png").openStream()));
            errorbar.setImage(new Image(HelloController.class.getResource("errorbarMINT.png").openStream()));
            tryagainbutton.setImage(new Image(HelloController.class.getResource("tryAgain.png").openStream()));
            emailButton.setImage(new Image(HelloController.class.getResource("greatButton.png").openStream()));
            emailText.setImage(new Image(HelloController.class.getResource("mailCopyed.png").openStream()));
            completeText.setImage(new Image(HelloController.class.getResource("completeBar.png").openStream()));
            completeButton.setImage(new Image(HelloController.class.getResource("greatButton.png").openStream()));

        }
    }
    @FXML
    protected void onDonateClick(){
        Main.donate();
    }
    @FXML protected void onKianoClick(){
        Main.kiano();
    }
    @FXML protected void onHelpClick(){
        StringSelection selection = new StringSelection(":3");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        emailPane.setDisable(false);
        emailPane.setVisible(true);
        WebDriver driver = new ChromeDriver();

        driver.get("_________");

    }
    @FXML
    protected void onCloseEmailClick(){
        emailPane.setDisable(true);
        emailPane.setVisible(false);
    }
    @FXML
    protected void onOpenSettitgsClick(){
        presettingspane.setVisible(true);
        settingspane.setVisible(true);
    }
    @FXML
    protected void onCloseSettingsClick(){
        presettingspane.setVisible(false);
        settingspane.setVisible(false);
    }
    @FXML
    protected void onOpenAuthClick(){
        authpane1.setVisible(true);
        whiteauth.setVisible(false);

    }
    @FXML
    protected void onCloseAuthClick(){
        authpane1.setVisible(false);
        whiteauth.setVisible(true);
        yauth.setVisible(false);
    }
    @FXML
    protected void onOpenYAuthClick(){
        yauth.setVisible(true);
        System.out.println("yauth");
    }
    @FXML
    protected void onCloseHelloClick(){
        helloPane.setDisable(true);
        helloPane.setVisible(false);
        System.out.println("closeHello");
    }
    @FXML
    protected void onCloseErrorClick(){
        errorPane.setVisible(false);
        errorPane.setDisable(true);
    }
    @FXML
    protected void onChangeModeClick(){
        if(!vkToY){
            change.setRotate(180);
        }else {
            change.setRotate(0);
        }

        vkToY = !vkToY;
        System.out.println("rotate");
    }
    @FXML
    protected void onStartClick() throws IOException, InterruptedException {
        if(!vkToY) {
            Main.fromVkToY();
        }else{
            Main.fromYToVk();
        }
        completePane.setDisable(false);
        completePane.setVisible(true);
    }
    @FXML
    protected void onLoginYClick(){
        Cursor c;
        wait = true;
        try {
            if( Main.loginY(logintext.getText(), passtext.getText())) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Успех!");
                al.setHeaderText("Вход в яндекс произошёл успешно");
                al.showAndWait();
                wait = false;
            }else{
                errorPane.setDisable(false);
                errorPane.setVisible(true);
                passtext.setText("");
                wait = false;
            }
        }catch (Exception e){
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Успех*-1");
            err.setHeaderText("Произошла ошибка. Попробуйте повторить вход через минуту");
            err.showAndWait();
            passtext.setText("");
            wait = false;
        }
    }

    @FXML
    protected void onLoginVkClick(){Main.loginVk();}

}