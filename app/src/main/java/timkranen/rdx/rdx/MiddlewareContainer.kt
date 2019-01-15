package timkranen.rdx.rdx

class MiddlewareContainer<State> {
    private val containedGroups = mutableListOf<MiddlewareGroup<State>>()

    val allMiddleware: List<Middleware<State>>
        get() = containedGroups.flatMap { it.getMiddleware() }

    fun addGroup(group: MiddlewareGroup<State>) {
        containedGroups.add(group)
    }

    fun addGroups(vararg groups: MiddlewareGroup<State>) {
        containedGroups.addAll(groups)
    }

    fun getGroupBy(identifier: String): MiddlewareGroup<State>? {
        return containedGroups.find { group -> group.getIdentifier() == identifier }
    }
}
