//package ru.lion.netmedia.adapter
//
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import ru.lion.netmedia.dto.Post
//import ru.lion.netmedia.viewmodel.PostViewModel
//
//typealias OnLikeListener = (post: Post) -> Unit
//
//class PostAdapter(private val onLikeListener: OnLikeListener) :
//    RecyclerView.Adapter<PostViewHolder>() {
//    var list = emptyList<Post>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//    }
//
//    override fun getItemCount(): Int = list.size
//    //TODO доделать!
//}