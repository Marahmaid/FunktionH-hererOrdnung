import kotlin.random.Random

class Linklist<T> : Listlike<T> , Iterable<T> {
    inner class Node(var data: T, var next: Node? = null)

    private var first: Node? = null

    override fun addFirst(data: T) {
        val next = first
        first = Node(data, next)
        //o von 1
    }

    override fun getFirst(): T {
        if (first == null)
            throw NoSuchElementException()
        return first!!.data
        //n=1
    }


    override fun addLast(data: T) {
        if (first == null) addFirst(data)
        else {
            var runPointer = first
            while (runPointer?.next != null) {
                runPointer = runPointer.next
            }
            runPointer?.next = Node(data, null)
        }
        //n
    }

    override fun removeFirst(): T {
        if (first == null) {
            throw NoSuchElementException()
        }
        val firstdata = first!!.data
        first = first?.next
        return firstdata
        //n=1
    }

    override fun size(): Int {
        var run = first
        var count = 0
        while (run != null) {
            count++
            run = run.next
        }
        return count
        //n
    }

    override fun contains(data: T): Boolean {
        var run = first
        while (run != null) {
            if (run.data == data) return true
            run = run.next
        }
        return false
        //n
    }

    override fun isEmpty(): Boolean = first == null
    //o = 1
    override fun get(index: Int): T {
        if (index < 0 || index >= size()) throw IndexOutOfBoundsException()
        var run = first
        var count = 0
        while (run != null) {
            if (count == index) {
                return run.data
            }
            count++
            run = run.next
        }
        return run?.data!!
        //n
    }

    override fun removeAtIndex(index: Int): T {
        var run = first
        var i = 0
        if (index >= this.size() || index < 0) {
            throw IndexOutOfBoundsException()
        }
        if (isEmpty()) {
            throw NoSuchElementException()
        }
        while (i < index - 1) { //2index -1 also 1index
            run = run?.next
            i++
        }
        val removed = run?.next
        run?.next = run?.next?.next

        return removed!!.data
    }
    //n
    fun any(f: (T) -> Boolean): Boolean {
        var suche = first
        while (suche != null) {
            if (f(suche.data)) {
                return true
            }
            suche = suche.next
        }
        return false
    }

    fun all(f: (T) -> Boolean): Boolean {
        var alle = first
        while (alle != null) {
            if (f(alle.data)) {
                alle = alle.next
            }
        }
        return false
    }

    fun find(f: (T) -> Boolean): T? {
        var suche = first
        while (suche != null) {
            if (f(suche.data)) {

                return suche!!.data
            }
            suche = suche.next
        }
        return null
    }

    fun forEach(f: (T) -> Unit) {
        var suche = first
        while (suche != null) {
            f(suche.data)
            suche = suche.next
        }
    }

    fun update (f : (T) -> T) {
        var suche = first
        while(suche != null) {
            suche.data = f(suche.data)
            suche = suche.next
        }
    }
    inner class elemtIterator() : Iterator <T>{
        var run = first
        override fun hasNext(): Boolean {
           return run != null
        }

        override fun next(): T {
            var ende = run?.data ?: throw NoSuchElementException()
            run = run?.next
            return ende
        }

    }

    override fun iterator(): Iterator<T> = elemtIterator()

}

fun main() {
    val list = Linklist<String>()
    list.addFirst("A")
    list.addFirst("B")
    list.addFirst("F")
    list.addFirst("C")

    println(list.all {data -> data.length >=1})
    println(list.any({ data -> data.length >= 10 }))

    val stringMits = list.find { data -> data.startsWith("F")}
    println(stringMits)

    list.update { data ->
        var string = ""
        string += data
        string += " NOCH EIN STRING "
        string += data.lowercase()
        string += Random.nextInt(0, 10)
        string
    }
    list.forEach {data -> println(data)}
    for (elemnt in list){
        println(list)
    }
    // c f b a
}

