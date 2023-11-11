import java.util.*;

class mySafeOperationIterator implements SafeOperationIterator {
    private Deque<SafeOperation> stack;

    public mySafeOperationIterator(SafeOperationSequence sequence) {
        this.stack = new ArrayDeque<>();
        pushLeafNodes(sequence);
    }

    private void pushLeafNodes(SafeOperationSequence sequence) {
        Stack<SafeOperationSequence> tempStack = new Stack<>();
        tempStack.push(sequence);

        while (!tempStack.isEmpty()) {
            SafeOperationSequence currentSequence = tempStack.pop();

            if (currentSequence.getFirst() instanceof SafeOperationSequence) {
                tempStack.push((SafeOperationSequence) currentSequence.getFirst());
            } else if (currentSequence.getFirst() instanceof SafeOperationSingle) {
                stack.push(currentSequence.getFirst());
            }

            if (currentSequence.getSecond() instanceof SafeOperationSequence) {
                tempStack.push((SafeOperationSequence) currentSequence.getSecond());
            } else if (currentSequence.getSecond() instanceof SafeOperationSingle) {
                stack.push(currentSequence.getSecond());
            }
        }
    }

    @Override
    // Throws a 'java.util.NoSuchElementException' if the iteration has no more elements.
    public SafeOperationSingle next() {
        if (hasNext()) {
            return (SafeOperationSingle) stack.pop();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
