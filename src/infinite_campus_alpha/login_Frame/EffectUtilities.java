/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinite_campus_alpha.login_Frame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 *
 * @author jeraldy
 */


public class EffectUtilities {
      public static void makeDraggable( Stage stage,  Node byNode) {
    final Delta dragDelta = new Delta();
    byNode.setOnMousePressed((MouseEvent mouseEvent) -> {
        // record a delta distance for the drag and drop operation.
        dragDelta.x = stage.getX() - mouseEvent.getScreenX();
        dragDelta.y = stage.getY() - mouseEvent.getScreenY();
        byNode.setCursor(Cursor.MOVE);
    });
    byNode.setOnMouseReleased((MouseEvent mouseEvent) -> {
        byNode.setCursor(Cursor.HAND);
    });
    byNode.setOnMouseDragged((MouseEvent mouseEvent) -> {
        stage.setX(mouseEvent.getScreenX() + dragDelta.x);
        stage.setY(mouseEvent.getScreenY() + dragDelta.y);
    });
    byNode.setOnMouseEntered((MouseEvent mouseEvent) -> {
        if (!mouseEvent.isPrimaryButtonDown()) {
            byNode.setCursor(Cursor.HAND);
        }
    });
    byNode.setOnMouseExited((MouseEvent mouseEvent) -> {
        if (!mouseEvent.isPrimaryButtonDown()) {
            byNode.setCursor(Cursor.DEFAULT);
        }
    });
  }

  /** records relative x and y co-ordinates. */
  private static class Delta {
    double x, y;
  }
}
