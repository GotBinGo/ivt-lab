package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GT4500Test {

  private GT4500 ship;
  private GT4500 ship2;
  private TorpedoStore mockPrimary, mockSecondary;

  @Before
  public void init() {
    mockPrimary = Mockito.mock(TorpedoStore.class);
    mockSecondary = Mockito.mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimary, mockSecondary);
    this.ship2 = new GT4500();
  }
    @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireLaser_default(){
 
    // Act
    boolean result = ship2.fireLaser(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void firePrimary_empty(){
 
    // Act
    boolean result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();
    result = ship2.firePrimary();

    // Assert
    assertEquals(false, result);
  }


  @Test
  public void fireSecondary_empty(){
 
    // Act
    boolean result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();
    result = ship2.fireSecondary();

    // Assert
    assertEquals(false, result);
    verify(ship2, times(13)).fireSecondary();
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Fail(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Primary_Fail(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Secondary_Fail(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(false);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE); // Primary should be fired again

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Primary_Fail(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Secondary_Fail(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Alternating_Fail() {
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(false);

    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);

    when(mockPrimary.fire(1)).thenReturn(false);

    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result1 && !result2);
  }
}
