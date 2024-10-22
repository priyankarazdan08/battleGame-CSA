package com.example.battlegame;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HelloController {
    @FXML
    public Label attemptsToCreateChar;
    @FXML
    public Label oppName;
    @FXML
    public Label displayNameInBattle;
    @FXML
    public ImageView oppImage, pickHP, pickHG, pickRW, itemView, oppImg, playerImg;
    @FXML
    public TitledPane characterCreatorPane;
    @FXML
    public ListView lstStore, lstInventory, lstOpps, lstViewStats, opponentStats, lstSpecialAbilities, eventLog, itemDetails;
    @FXML
    public Button createStatsBtn, buyBtn;
    public AnchorPane battleScreen;
    public ListView potionsEquipped;
    public ListView lstWeaponsEquipped;
    public Label oppHPLeft;
    public Label playerHPLeft;
    public TitledPane payoutScreen;
    public Label gotXP;
    public Label gotHP;
    public Label randomItem;
    public TitledPane characterDetailView;
    public ListView characterStats;
    public TitledPane revivalStore;
    public ListView potionLstView;
    public Label characterXPBattle;
    public Label characterXP;
    public BarChart barChart;
    public TitledPane youWinStats;
    public TitledPane youWinBattle;
    public TitledPane youWinStore;
    private ArrayList<Opponent> oppsPlayed = new ArrayList<>();
    private String[] displayItems = new String[] {"Felix Felicis, 50 XP", "Sword Of Gryffindor, 500 XP", "Nimbus 2000, 100 XP", "Invisiblity Cloak, 800 XP", "Dittany, 200 XP", "Moly, 200 XP", "Aconite, 200 XP", "Dragon Heartstring Rowan, 900 XP", "Phoenix Feather Pear, 900 XP", "Horned Serpent Horn Walnut, 1000 XP"};
    private String[] displayOpps = new String[] {"Welsh Green", "Chinese Fireball", "Hungarian Horntail", "Chimera", "Hippogriff", "Acromantula", "Basilisk"};
    private Player mc;
    private Opponent oppClicked;
    private Items itemClicked;
    private int attemptToMakeChar = 3;

    /*
    pre: none; runs on-load
    post: displays opponents and items in listviews
     */
    public void initialize(){
        characterCreatorPane.setVisible(true);
        attemptsToCreateChar.setText("Attempts Left: " + attemptToMakeChar);
        for(String item: displayItems) {
            lstStore.getItems().add(item);
        }
        for(String opp: displayOpps) {
            lstOpps.getItems().add(opp);
        }
        characterXP.setVisible(false);
    }

    public void updateListViews() {
        characterXP.setText("XP Available: " + mc.getXP());
        characterXPBattle.setText("XP Available: " + mc.getXP());
    }




    public void handleLstInventory(MouseEvent mouseEvent) throws FileNotFoundException {
        String itemName = lstInventory.getSelectionModel().getSelectedItem().toString();
        itemClicked = new Items(itemName);
        FileInputStream item = new FileInputStream(itemClicked.setItemImage());
        itemView.setImage(new Image(item));
        itemDetails.getItems().clear();
        for(String stat: itemClicked.getType(itemClicked)) {
            itemDetails.getItems().add(stat);
        }
        buyBtn.setVisible(false);

    }

    public void handleLstStore(MouseEvent mouseEvent) throws FileNotFoundException {
        String thingClicked = lstStore.getSelectionModel().getSelectedItem().toString();
        String itemName = thingClicked.substring(0, thingClicked.indexOf(","));
        itemClicked = new Items(itemName);
        FileInputStream item = new FileInputStream(itemClicked.setItemImage());
        itemView.setImage(new Image(item));
        itemDetails.getItems().clear();
        for(String stat: itemClicked.getType(itemClicked)) {
            itemDetails.getItems().add(stat);
        }
        buyBtn.setVisible(true);

    }

    @FXML
    public void handleLstOpps(MouseEvent mouseEvent) throws FileNotFoundException {
        String thingClicked = lstOpps.getSelectionModel().getSelectedItem().toString();
        boolean wentIn = false;
        if(!oppsPlayed.isEmpty()) {
            for(Opponent opp: oppsPlayed) {
                if(opp.getName().equals(thingClicked)) {
                    wentIn = true;
                    oppClicked = opp;
                }
            }
            if(!wentIn) {
                oppClicked = new Opponent(thingClicked);
            }
        }
        else {
            oppClicked = new Opponent(thingClicked);
        }

        FileInputStream opponent = new FileInputStream(oppClicked.setOppImage());
        oppImage.setImage(new Image(opponent));
        oppName.setText(oppClicked.getName());

        opponentStats.getItems().clear();
        for(String stat: oppClicked.setStats(mc)) {
            opponentStats.getItems().add(stat);
        }

    }

    public void handleHarryPotter(MouseEvent mouseEvent) {
        DropShadow dropShadow = new DropShadow();
        pickHP.setEffect(dropShadow);
        pickHG.setEffect(null);
        pickRW.setEffect(null);
        mc = new Player("Harry Potter");
    }

    public void handleRonWeasley(MouseEvent mouseEvent) {
        DropShadow dropShadow = new DropShadow();
        pickRW.setEffect(dropShadow);
        pickHP.setEffect(null);
        pickHG.setEffect(null);
        mc = new Player("Ron Weasley");
    }

    public void handleHermioneGranger(MouseEvent mouseEvent) {
        DropShadow dropShadow = new DropShadow();
        pickHG.setEffect(dropShadow);
        pickHP.setEffect(null);
        pickRW.setEffect(null);
        mc = new Player("Hermione Granger");
    }

    public void createRandomStats(ActionEvent actionEvent) {
        lstViewStats.getItems().clear();
        attemptToMakeChar -= 1;
        for(int i = 0; i < mc.createRandomStats().length; i++) {
            lstViewStats.getItems().add(mc.createRandomStats()[i]);
        }
        attemptsToCreateChar.setText("Attempts Left: " + attemptToMakeChar);
        if (attemptToMakeChar < 1) {
            createStatsBtn.setDisable(true);
        }
    }


    public void setOpp(ActionEvent actionEvent) throws FileNotFoundException {
        oppClicked.setNumOfPlays(1);
        oppsPlayed.add(oppClicked);
        battleScreen.setVisible(true);
        lstWeaponsEquipped.getItems().clear();
        potionsEquipped.getItems().clear();
        lstSpecialAbilities.getItems().clear();
        if(!mc.getInventory().isEmpty()) {
            for(ItemsOwned item: mc.getInventory()) {
                if(item.getItem().returnType().equals("Weapon") || item.getItem().returnType().equals("Wand") || item.getItem().returnType().equals("Defence")) {
                    lstWeaponsEquipped.getItems().add(item.getItem().getName());
                }
                if(item.getItem().returnType().equals("Potion") || item.getItem().returnType().equals("Herb")) {
                    potionsEquipped.getItems().add(item.getItem().getName());
                }
            }
        }
        lstSpecialAbilities.getItems().add(oppClicked.getSpecialAbility().getName());
        lstSpecialAbilities.getItems().add(oppClicked.getRandomItem().getName());
        displayNameInBattle.setText(oppClicked.getName());
        FileInputStream player = new FileInputStream(mc.setPlayerImage(mc.getName()));
        playerImg.setImage(new Image(player));
        FileInputStream opp = new FileInputStream(oppClicked.setOppImage());
        oppImg.setImage(new Image(opp));

        playerHPLeft.setText(mc.getHP() + "");
        oppHPLeft.setText(oppClicked.getHP() + "");


    }

    public void buyItem(ActionEvent actionEvent) {
        String thingClicked = lstStore.getSelectionModel().getSelectedItem().toString();
        String itemName = thingClicked.substring(0, thingClicked.indexOf(","));
        String itemCost = thingClicked.substring(thingClicked.indexOf(",") + 2, thingClicked.indexOf("X") - 1);
        if(mc.getXP() >= Integer.parseInt(itemCost)) {
            mc.setXP(-(Integer.parseInt(itemCost)));
            boolean wentIn = false;
            if(!mc.getInventory().isEmpty()) {
                for(ItemsOwned item: mc.getInventory()) {
                    if(item.getItem().getName().equals(itemName)) {
                        wentIn = true;
                        item.setNumOwned(1);
                    }
                }
                if(!wentIn) {
                    mc.getInventory().add(new ItemsOwned(new Items(itemName), 1));
                }
            }
            else {
                mc.getInventory().add(new ItemsOwned(new Items(itemName), 1));;
            }

            lstInventory.getItems().clear();
            for(ItemsOwned item: mc.getInventory()) {
                lstInventory.getItems().add(item.getItem().getName());
            }
        }
        updateListViews();

    }

    public void closeCreatorPane(ActionEvent actionEvent) {
        characterCreatorPane.setVisible(false);
        characterXP.setVisible(true);
        updateListViews();
    }

    public void takeATurn(ActionEvent actionEvent) {
        for(String log: mc.battle(oppClicked)) {
            eventLog.getItems().add(log);
        }
        playerHPLeft.setText(mc.getHP() + "Left");
        oppHPLeft.setText(oppClicked.getHP() + "Left");

        for(ItemsOwned item: mc.getInventory()) {
            if(item.getNumOwned() < 1) {
                mc.getInventory().remove(item);
            }
        }

        lstWeaponsEquipped.getItems().clear();
        potionsEquipped.getItems().clear();
        for(ItemsOwned item: mc.getInventory()) {
            if(item.getItem().returnType().equals("Weapon") || item.getItem().returnType().equals("Defence")) {
                lstWeaponsEquipped.getItems().add(item.getItem().getName());
            }
            if(item.getItem().returnType().equals("Potion") || item.getItem().returnType().equals("Herb")) {
                potionsEquipped.getItems().add(item.getItem().getName());
            }
        }
    }

    public void closeBattle(ActionEvent actionEvent) {
        battleScreen.setVisible(false);
        payoutScreen.setVisible(true);
        if(mc.getHP() > 0) {
            mc.setXP(1000/(10 - oppClicked.getWorth()));
            gotXP.setText("XP Recieved: " + 1000/(10 - oppClicked.getWorth()));
            mc.setHP(500);
            gotHP.setText("HP Recieved: " + 500);
            int randomIndex = (int) (Math.random()*displayItems.length - 3);
            String thingClicked = displayItems[randomIndex];
            String itemName = thingClicked.substring(0, thingClicked.indexOf(","));
            randomItem.setText("Random ITEM found: " + itemName);
            Items item = new Items(itemName);
            mc.getInventory().add(new ItemsOwned(item, 1));
        }
        else {
            gotXP.setText("XP Recieved: " + 0);
            mc.setHP(500);
            gotHP.setText("HP Recieved: " + 500);
            randomItem.setText("No Item Recieved.")
        }

        opponentStats.getItems().clear();
        oppName.setText(oppClicked.getName());
        for(String stat: oppClicked.setStats(mc)) {
            opponentStats.getItems().add(stat);
        }
        lstInventory.getItems().clear();
        for(ItemsOwned item: mc.getInventory()) {
            lstInventory.getItems().add(item.getItem().getName());
        }
        createBarChart();
        eventLog.getItems().clear();
        oppClicked.setHP(mc.getHP()/(10 - oppClicked.getWorth()));

    }

    public void closePayout(ActionEvent actionEvent) {
        payoutScreen.setVisible(false);
        eventLog.getItems().clear();
        if(mc.getXP() > 2000) {
            youWinBattle.setVisible(true);
            youWinStats.setVisible(true);
            youWinStore.setVisible(true);
        }
    }

    public void handleSave1(ActionEvent actionEvent) {
        String outFile = "src/main/resources/load1";
        try {
            PrintWriter out = new PrintWriter(outFile);
            out.println(mc.getName());
            for(ItemsOwned item: mc.getInventory()) {
                out.println(item.getItem().getName() + "," + item.getNumOwned());
            }
            out.println(mc.getXP());
            out.println(mc.getHP());
            out.println(mc.getDefenceADarkArtsSkill());
            out.println(mc.getPotionSkill());
            out.println(mc.getHerbologySkill());

            for(Opponent opp: oppsPlayed) {
                out.println(opp.getName() + "," + opp.getNumOfPlays());
            }
            out.close();
        } catch (FileNotFoundException x) {
            System.out.println("no file");
        }

    }

    public void handleLoad1(ActionEvent actionEvent) {
        try {
            FileReader reader = new FileReader("src/main/resources/load1");
            Scanner in = new Scanner(reader);
            mc = new Player(in.nextLine());
            int length = mc.getInventory().size();
            mc.getInventory().clear();
            for(int i = 0; i < length; i++) {
                mc.getInventory().add(new ItemsOwned(new Items (in.nextLine().substring(0,in.nextLine().indexOf(","))), Integer.parseInt(in.nextLine().substring(in.nextLine().indexOf(",") + 1))));
                System.out.println(mc.getInventory().toString());
            }
            if(in.hasNextInt()) {
                mc.loadXP(in.nextInt());
            }
            if(in.hasNextInt()) {
                mc.loadHP(in.nextInt());
            }
            if(in.hasNextInt()) {
                mc.loadDefence(in.nextInt());
            }
            if(in.hasNextInt()) {
                mc.loadPotion(in.nextInt());
            }
            if(in.hasNextInt()) {
                mc.loadHerb(in.nextInt());
            }
            for(String x: mc.getStats()) {
                System.out.println(x);
            }
            length = oppsPlayed.size();
            oppsPlayed.clear();
            if(in.hasNextLine()) {
                for (int i = 0; i < length - 1; i++) {
                    oppsPlayed.add(new Opponent(in.nextLine().substring(0, in.nextLine().indexOf(",")), (Integer.parseInt(in.nextLine().substring(in.nextLine().indexOf(",") + 1)))));
                }
            }
        } catch (FileNotFoundException x) {
            System.out.println("no file");
        }
    }

    public void handleSave2(ActionEvent actionEvent) {
        String outFile = "src/main/resources/load2";
        try {
            PrintWriter out = new PrintWriter(outFile);
            out.println(mc.getName());
            for(ItemsOwned item: mc.getInventory()) {
                out.println(item.getItem().getName() + "," + item.getNumOwned());
            }
            out.println(mc.getXP());
            out.println(mc.getHP());
            out.println(mc.getDefenceADarkArtsSkill());
            out.println(mc.getPotionSkill());
            out.println(mc.getHerbologySkill());

            for(Opponent opp: oppsPlayed) {
                out.println(opp.getName() + "," + opp.getNumOfPlays());
            }
            out.close();
        } catch (FileNotFoundException x) {
            System.out.println("no file");
        }
    }

    public void handleLoad2(ActionEvent actionEvent) {
        try {
            FileReader reader = new FileReader("src/main/resources/load2");
            Scanner in = new Scanner(reader);
            mc = new Player(in.nextLine());
            int length = mc.getInventory().size();
            mc.getInventory().clear();
            for(int i = 0; i < length; i++) {
                mc.getInventory().add(new ItemsOwned(new Items (in.nextLine().substring(0,in.nextLine().indexOf(","))), Integer.parseInt(in.nextLine().substring(in.nextLine().indexOf(",") + 1))));
                System.out.println(mc.getInventory().toString());
            }
            if(in.hasNextInt()) {
                mc.loadXP(in.nextInt());
            }
            if(in.hasNextInt()) {
                mc.loadHP(in.nextInt());
            }
            if(in.hasNextInt()) {
                mc.loadDefence(in.nextInt());
            }
            if(in.hasNextInt()) {
                mc.loadPotion(in.nextInt());
            }
            if(in.hasNextInt()) {
                mc.loadHerb(in.nextInt());
            }
            length = oppsPlayed.size();
            oppsPlayed.clear();
            if(in.hasNextLine()) {
                for (int i = 0; i < length - 1; i++) {
                    oppsPlayed.add(new Opponent(in.nextLine().substring(0, in.nextLine().indexOf(",")), (Integer.parseInt(in.nextLine().substring(in.nextLine().indexOf(",") + 1)))));
                }
            }
            System.out.println(mc.getXP());
        } catch (FileNotFoundException x) {
            System.out.println("no file");
        }
    }

    public void characterDetail(ActionEvent actionEvent) {
        characterDetailView.setVisible(true);
        characterStats.getItems().clear();
        for(String stat: mc.getStats()) {
            characterStats.getItems().add(stat);
        }
    }

    public void closeCharacterDetailPane(ActionEvent actionEvent) {
        characterDetailView.setVisible(false);
    }

    public void buyRevival(ActionEvent actionEvent) {
        String itemName = potionLstView.getSelectionModel().getSelectedItem().toString();
        Items tempItem = new Items(itemName);
        if(mc.getXP() >= tempItem.getCost()) {
            mc.setXP(-tempItem.getCost());
            boolean wentIn = false;
            if(!mc.getInventory().isEmpty()) {
                for(ItemsOwned item: mc.getInventory()) {
                    if(item.getItem().getName().equals(itemName)) {
                        item.setNumOwned(1);
                        wentIn = true;
                    }
                }
                if(!wentIn) {
                    mc.getInventory().add(new ItemsOwned(new Items (itemName), 1));
                }
            }
            else {
                mc.getInventory().add(new ItemsOwned(new Items (itemName), 1));
            }
        }
    }

    public void closePane(ActionEvent actionEvent) {
        revivalStore.setVisible(false);
    }

    public void openRevivals(ActionEvent actionEvent) {
        revivalStore.setVisible(true);
        potionLstView.getItems().clear();
        potionLstView.getItems().addAll("Felix Felicis", "Invisiblity Cloak", "Dittany");
    }

    public void createBarChart() {
        double tempWelshWins = 0.0;
        double tempWelshLoss = 0.0;
        double tempBasWins = 0.0;
        double tempBasLoss = 0.0;
        double tempChiWins = 0.0;
        double tempChiLoss = 0.0;
        double tempHunWins = 0.0;
        double tempHunLoss = 0.0;
        double tempChimWins = 0.0;
        double tempChimLoss = 0.0;
        double tempHippoWins = 0.0;
        double tempHippoLoss = 0.0;
        double tempAcroLoss = 0.0;
        double tempAcroWins = 0.0;
        for (Opponent opp : oppsPlayed) {
            if (opp.getName().equals("Welsh Green")) {
                tempWelshWins = opp.getWins();
                tempWelshLoss = opp.getLosses();
            }
            if (opp.getName().equals("Basilisk")) {
                tempBasWins = opp.getWins();
                tempBasLoss = opp.getLosses();
            }
            if (opp.getName().equals("Chinese Fireball")) {
                tempChiWins = opp.getWins();
                tempChiLoss = opp.getLosses();
            }
            if (opp.getName().equals("Hungarian Horntail")) {
                tempHunWins = opp.getWins();
                tempHunLoss = opp.getLosses();
            }
            if (opp.getName().equals("Chimera")) {
                tempChimWins = opp.getWins();
                tempChimLoss = opp.getLosses();
            }
            if (opp.getName().equals("Hippogriff")) {
                tempHippoWins = opp.getWins();
                tempHippoLoss = opp.getLosses();
            }
            if (opp.getName().equals("Acromantula")) {
                tempAcroWins = opp.getWins();
                tempAcroLoss = opp.getLosses();
            }
        }

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Welsh Green");
        series1.getData().add(new XYChart.Data<>("Wins", tempWelshWins));
        series1.getData().add(new XYChart.Data<>("Losses", tempWelshLoss));
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Chinese Fireball");
        series2.getData().add(new XYChart.Data<>("Wins", tempChiWins));
        series2.getData().add(new XYChart.Data<>("Losses", tempChiLoss));
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Hippogriff");
        series3.getData().add(new XYChart.Data<>("Wins", tempHippoWins));
        series3.getData().add(new XYChart.Data<>("Losses", tempHippoLoss));
        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("Acromantula");
        series4.getData().add(new XYChart.Data<>("Wins", tempAcroWins));
        series4.getData().add(new XYChart.Data<>("Losses", tempAcroLoss));
        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("Chimera");
        series5.getData().add(new XYChart.Data<>("Wins", tempChimWins));
        series5.getData().add(new XYChart.Data<>("Losses", tempChimLoss));
        XYChart.Series<String, Number> series6 = new XYChart.Series<>();
        series6.setName("Basilisk");
        series6.getData().add(new XYChart.Data<>("Wins", tempBasWins));
        series6.getData().add(new XYChart.Data<>("Losses", tempBasLoss));
        XYChart.Series<String, Number> series7 = new XYChart.Series<>();
        series7.setName("Hungarian Horntail");
        series7.getData().add(new XYChart.Data<>("Wins", tempHunWins));
        series7.getData().add(new XYChart.Data<>("Losses", tempHunLoss));

        barChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
    }

    public void closeWinScreenStore(ActionEvent actionEvent) {
        youWinStore.setVisible(false);
    }

    public void closeWinScreenStats(ActionEvent actionEvent) {
        youWinStats.setVisible(false);
    }

    public void closeWinScreenBattle(ActionEvent actionEvent) {
        youWinBattle.setVisible(false);
    }
}