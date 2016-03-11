package com.bhop.game.util;

import org.newdawn.slick.Input;

public final class InputUtils
{
	
	private static boolean leftMouseButtonPressed;
	
	private InputUtils() {}

	public static boolean isLeftMouseButtonPressed()
	{
		return leftMouseButtonPressed;
	}

	public static void updateInput(Input input)
	{
		leftMouseButtonPressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
	}

}
