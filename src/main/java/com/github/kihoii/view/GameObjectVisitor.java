package com.github.kihoii.view;

public interface GameObjectVisitor {

    void visit(GameObject.Pacman pacman);

    void visit(GameObject.Ghost block);

    void visit(GameObject.Point block);

}
