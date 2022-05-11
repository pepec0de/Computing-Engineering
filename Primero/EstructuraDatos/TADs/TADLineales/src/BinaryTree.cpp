#include "BinaryTree.hpp"

BinaryTree::BinaryTree() {
    root = new Node;
    if (root != NULL) {
        root->left = NULL;
        root->right = NULL;
        root->data = -1;
        lastNode = root;
    }
}

BinaryTree::BinaryTree(int i) {
    root = new Node;
    if (root != NULL) {
        root->left = NULL;
        root->right = NULL;
        root->data = i;
        lastNode = root;
    }
}

BinaryTree::~BinaryTree() {
    //dtor
}

bool BinaryTree::addNode(Node *parentNode, int data) {
    // Comprobamos que el nodo padre no tiene los dos nodos hijos ocupados
    if (hasChild(parentNode) != 3) {
        Node *newNode = new Node;
        // Comprobamos que tenemos memoria
        if (newNode != NULL) {
            newNode->left = NULL;
            newNode->right = NULL;
            newNode->data = data;

            if (parentNode->left == NULL) {
                parentNode->left = newNode;
            } else {
                parentNode->right = newNode;
            }
            return true;
        }
    }
    return false;
}

bool BinaryTree::addNode(int data) {
    Node *newNode = new Node;
    newNode->left = NULL;
    newNode->right = NULL;
    newNode->data = data;
    if (newNode != NULL) {
        Node *aux;
        if (lastNode->left == NULL) {
            lastNode->left = newNode;
            cont++;
        } else {
            lastNode->right = newNode;
            aux = newNode;
            lastNode = lastNode->left;
            cont++;
            if (cont == 1) {
                lastNode = aux;
                cont = 0;
            }
        }
        return true;
    }
    return false;
}

bool BinaryTree::hasChildren(Node *node) {
    return (node->left != NULL || node->right != NULL);
}

int BinaryTree::hasChild(Node *node) {
    // 0 no children
    // 1 left child
    // 2 right child
    // 3 both children
    if (!hasChildren(node)) {
        return 0;
    }
    if (node->left != NULL && node->right == NULL) {
        return 1;
    }
    if (node->left == NULL && node->right != NULL) {
        return 2;
    }
    return 3;
}

string tostring(int value) {
    ostringstream strs;
    strs << value;
    return strs.str();
}

void BinaryTree::printBinaryTree() {
    string tree = "";
    tree += tostring(root->data) + "(" + (root->left == NULL ? " " : getNodeAsString(root->left))
        + "," + (root->right == NULL ? " " : getNodeAsString(root->right)) + ")";
    cout << tree << endl;
}

string BinaryTree::getNodeAsString(Node *node) {
    return (tostring(node->data) + "(" + (node->left == NULL ? " " : getNodeAsString(node->left))
        + "," + (node->right == NULL ? " " : getNodeAsString(node->right)) + ")");
}
