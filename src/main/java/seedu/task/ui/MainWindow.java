<?xml version="1.0" encoding="UTF-8"?>

<?import java.net.URL?>
<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Menu?>
<?import javafx.scene.control.MenuBar?>
<?import javafx.scene.control.MenuItem?>
<?import javafx.scene.control.SplitPane?>
<?import javafx.scene.control.ToolBar?>
<?import javafx.scene.effect.ColorAdjust?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.text.Font?>
<?import javafx.scene.text.Text?>

<VBox maxHeight="Infinity" maxWidth="Infinity" minHeight="-Infinity" minWidth="-Infinity" xmlns="http://javafx.com/javafx/8.0.65" xmlns:fx="http://javafx.com/fxml/1" fx:controller="seedu.task.ui.MainWindow">
   <stylesheets>
      <URL value="@DarkTheme.css" />
      <URL value="@Extensions.css" />
   </stylesheets>
   <children>
      <ToolBar nodeOrientation="LEFT_TO_RIGHT" prefHeight="65.0" prefWidth="692.0" styleClass="background">
         <items>
            <Text fill="WHITE" strokeType="OUTSIDE" strokeWidth="0.0" text=" never forget" wrappingWidth="393.89601135253906">
               <font>
                  <Font name="Ikaros-Light" size="36.0" />
               </font>
            </Text>
         </items>
         <effect>
            <ColorAdjust brightness="0.05" />
         </effect>
      </ToolBar>
      <MenuBar VBox.vgrow="NEVER">
         <menus>
            <Menu mnemonicParsing="false" text="Help">
               <items>
                  <MenuItem fx:id="helpMenuItem" mnemonicParsing="false" onAction="#handleHelp" text="Help" />
               </items>
            </Menu>
         </menus>
      </MenuBar>
      <AnchorPane fx:id="commandBoxPlaceholder" styleClass="anchor-pane-with-border" VBox.vgrow="NEVER">
         <padding>
            <Insets bottom="5.0" left="10.0" right="10.0" top="5.0" />
         </padding>
      </AnchorPane>
       <AnchorPane fx:id="resultDisplayPlaceholder" maxHeight="100" minHeight="100" prefHeight="100" styleClass="anchor-pane-with-border" VBox.vgrow="NEVER">
           <padding>
               <Insets bottom="5.0" left="10.0" right="10.0" top="5.0" />
           </padding>
       </AnchorPane>
      <SplitPane id="splitPane" fx:id="splitPane" dividerPositions="0.4" maxHeight="1.7976931348623157E308" prefHeight="653.0" prefWidth="691.0">
         <items>
            <VBox fx:id="taskList" minWidth="340" prefWidth="340">
               <padding>
                  <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
               </padding>
               <children>
                  <AnchorPane fx:id="taskListPanelPlaceholder" VBox.vgrow="ALWAYS" />
                  <AnchorPane fx:id="statusbarPlaceholder" />
               </children>
            </VBox>
            <VBox fx:id="markedTaskList" prefHeight="200.0" prefWidth="100.0">
               <children>
                  <AnchorPane fx:id="markedTaskListPanelPlaceholder" VBox.vgrow="ALWAYS">
                     <padding>
                        <Insets bottom="10" left="10" right="10" top="10" />
                     </padding>
                  </AnchorPane>
               </children>
            </VBox>
         </items>
      </SplitPane>
   </children>
</VBox>
