package com.yksdy.desigin.patterns.behavioral.visitor;

public interface ComputerPart {
	public void accept(ComputerPartVisitor computerPartVisitor);
}
