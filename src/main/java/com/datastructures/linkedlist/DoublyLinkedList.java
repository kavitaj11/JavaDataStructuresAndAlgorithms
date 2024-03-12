package com.datastructures.linkedlist;

public class DoublyLinkedList<T extends Comparable<T>>  {
    private DLLNode<T> head;
    private DLLNode<T> tail;
    private int size;
    /**
     * Insert data at the end of the list
     * */

    public void insert(T data) {
        insertAtFront(data);
    }

    public void insertAfter(T data, int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("DoublyLinkedList is currently of size " + this.size);
        }
        // Get node at a specific index
        DLLNode<T> currentNode = getNodeAt(index);
        // Add node
        insertNode(data, currentNode);
    }

    public void remove(T data) {
        DLLNode<T> currentNode = this.head;
        while (currentNode != null) {
            T currentData = currentNode.getData();
            // Found data to remove
            if (currentData.compareTo(data) == 0) {
                removeNode(currentNode);
            }
            currentNode = currentNode.getNext();
        }
    }


    public void removeFromFront() {
        if (this.head != null) {
            DLLNode<T> nextNode = this.head.getNext();
            // Set prev node of next to null if it exists
            if (nextNode != null) {
                nextNode.setPrev(null);
            }
            // Remove current head
            this.head = null;
            this.head = nextNode;
            this.size--;
        }
    }

    public void removeFromBack() {
        if (this.head != null) {
            if (this.tail != null) {
                // Two or more elements exist
                DLLNode<T> newTail = this.tail.getPrev();
                this.tail = null;
                newTail.setNext(null);
                this.tail = newTail;
            } else {
                // Tail is null but head is not null
                // means that this is the last element
                this.head = null;
            }
            this.size--;
        }
    }


    public void removeAt(int index) throws IndexOutOfBoundsException {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("DoublyLinkedList is currently of size " + this.size);
        }
        DLLNode<T> nodeToRemove = getNodeAt(index);
        removeNode(nodeToRemove);
    }


    public void print() {
        System.out.println(this.toString());
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        DLLNode<T> currentNode = this.head;
        StringBuilder sb = new StringBuilder();
        sb.append("DoublyLinkedList: [size = ");
        sb.append(this.size).append(", Nodes=[");
        while (currentNode != null) {
            sb.append(currentNode.getData()).append(" --> ");
            currentNode = currentNode.getNext();
        }
        sb.append("null]]");
        return sb.toString();
    }

    /**
     * =========================================
     * ============ PRIVATE METHODS ============
     * =========================================
     * */

    /**
     * @return <code>true</code> if index is closer to end of linked list.
     * Return <code>false</code> otherwise.
     * */
    private boolean isCloserToEnd(int index) {
        // If index / size is greater than 0.5,
        // return 1. Otherwise, 0.
        return ((int) Math.round(((double)index / this.size))) == 1;
    }

    private DLLNode<T> getNodeAt(int index) {
        DLLNode<T> currentNode;
        int counter;
        if (isCloserToEnd(index)) {
            currentNode = this.tail;
            counter = this.size - 1;
            System.out.println("iterating from the tail");
            while (currentNode != null && counter != index) {
                currentNode = currentNode.getPrev();
                counter--;
            }
        } else {
            currentNode = this.head;
            counter = 0;
            System.out.println("iterating from the head");
            while (currentNode != null && counter != index) {
                currentNode = currentNode.getNext();
                counter++;
            }
        }
        return currentNode;
    }

    private void insertAtFront(T data) {
        if (this.head == null) {
            this.head = new DLLNode<>(data);
            // Tail and head cannot point at the same node
            this.tail = null;
        } else {
            if (this.tail == null) {
                // @Credit @alphaveneno Thank you for pointing out this mistake
                // Current head becomes new tail,
                // Since we are adding the second element
                this.tail = this.head;
                // Set new head
                this.head = new DLLNode<>(data);
                // Lastly, update references
                // Head --> Tail
                // Head <-- Tail
                this.head.setNext(this.tail);
                this.tail.setPrev(this.head);
            } else {
                DLLNode<T> prevHead = this.head;
                DLLNode<T> newHead = new DLLNode<>(data);

                // Update references
                // newHead.next --> prevHead
                // prevHead.prev <-- newHead
                newHead.setNext(prevHead);
                prevHead.setPrev(newHead);
                this.head = newHead;
            }
        }
        this.size++;
    }

    private void insertAtBack(T data) {
        if (this.head == null) {
            DLLNode<T> nodeToInsert = new DLLNode<>(data);
            this.head = nodeToInsert;
            // Tail and head cannot point at the same node
            this.tail = null;
        } else {
            //
            if (this.tail == null) {
                this.tail = new DLLNode<>(data);
                // Update references
                // Head --> Tail
                // Head <-- Tail
                this.head.setNext(this.tail);
                this.tail.setPrev(this.head);
            } else {
                DLLNode<T> prevTail = this.tail;
                DLLNode<T> newTail = new DLLNode<>(data);

                // Update references
                // prevTail --> newTail
                // prevTail <-- newTail
                newTail.setPrev(prevTail);
                prevTail.setNext(newTail);
                this.tail = newTail;
            }
        }
        this.size++;
    }

    private void insertNode(T dataToInsert,
                            DLLNode<T> currentNode ) {

        DLLNode<T> newNode = new DLLNode<>(dataToInsert);
        DLLNode<T> nextNode=currentNode.getNext();
        // If next node is null, current node is the tail node
        if (nextNode == null) {
            System.out.println("Inserting tail node");
            this.tail = newNode;
            this.tail.setPrev(currentNode);
            currentNode.setNext(newNode);
        } else {
            // Has both next and previous node.
            // Is somewhere in the middle of the linked list.
            System.out.println("Inserting non head/tail node");

            // First, have both prev and next node
            // point to node to insert
            currentNode.setNext(newNode);
            nextNode.setPrev(newNode);

            // Afterwards, have node to insert
            // prevNode point back to prev node.
            // and nextNode point to next node
            newNode.setPrev(currentNode);
            newNode.setNext(nextNode);
        }
        // NOTE: We don't have to worry
        // about the case where list is empty
        // If list is empty, we will throw an exception
        // Because we can't insert after an element,
        // if there are no elements in the list
        // to begin with.
        this.size++;
    }

    private void removeNode(DLLNode<T> nodeToRemove) {
        DLLNode<T> prevNode = nodeToRemove.getPrev();
        DLLNode<T> nextNode = nodeToRemove.getNext();
        // Head node does not have a previous node
        if (prevNode == null) {
            this.head = null;
            this.head = nextNode;
            this.head.setPrev(null);
        } else if (nextNode == null) {
            // Tail does not have a next node
            this.tail = null;
            this.tail = prevNode;
            this.tail.setNext(null);
        } else {
            // Is somewhere in the middle of the linked list
            // Set current node to null
            nodeToRemove = null;
            // connect the previous and next nodes together
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
        this.size--;
    }




    public class DLLNode<T> {

        private T data;
        private DLLNode<T> nextNode;
        private DLLNode<T> prevNode;

        public DLLNode(T data) {
            this.data = data;
            prevNode = null;
            nextNode = null;
        }

        public DLLNode(T data, DLLNode prev, DLLNode next) {
            this.data = data;
            this.prevNode = prev;
            this.nextNode = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public DLLNode<T> getNext() {
            return nextNode;
        }

        public DLLNode<T> getPrev() {
            return prevNode;
        }

        public void setNext(DLLNode<T> nextNode) {
            this.nextNode = nextNode;
        }

        public void setPrev(DLLNode<T> prevNode) {
            this.nextNode = prevNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
