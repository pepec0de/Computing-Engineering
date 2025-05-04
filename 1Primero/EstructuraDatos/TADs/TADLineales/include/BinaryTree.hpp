#ifndef BINARYTREE_HPP
#define BINARYTREE_HPP

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

struct Node {
    int data;
    Node *left;
    Node *right;
};

class BinaryTree
{
    private:
        Node *root;
        Node *lastNode;
        int cont = 0;

    public:
        BinaryTree();
        BinaryTree(int i);
        ~BinaryTree();
        bool addNode(int data);
        bool addNode(Node *parentNode, int data);
        bool hasChildren(Node *node);
        int hasChild(Node *node);
        void printBinaryTree();
        string getNodeAsString(Node *node);

};

#endif // BINARYTREE_HPP
