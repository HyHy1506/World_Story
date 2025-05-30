package com.example.worldstory.duc.ducutils


import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.widget.ScrollView
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.CardStoryItemLayoutBinding
import com.example.myapplication.databinding.ListCardStoriesLayoutBinding
import com.example.worldstory.view.RoleEnum
import com.example.worldstory.duc.SampleDataStory
import com.example.worldstory.view.ducactivity.DucStoriesByGenreActivity
import com.example.worldstory.view.ducactivity.DucStoryOverviewActivity
import com.example.worldstory.data.ducdataclass.DucParagraphDataClass
import com.example.worldstory.data.ducdataclass.DucStoryDataClass
import com.example.worldstory.data.model.Genre
import com.example.worldstory.data.model.Story
import com.squareup.picasso.Picasso
import org.mindrot.jbcrypt.BCrypt

import java.io.Serializable

//-------------------------------------


fun getDataNotFound(context: Context): String {
    return context.getString(R.string.dataNotFound)
}

var GUEST = 4
var ADMIN = 1
var AUTHOR = 2
var MEMBER = 3
var numDef = 1
fun getLoremIpsum(context: Context): String = context.getString(R.string.loremIpsum)
fun getLoremIpsumLong(context: Context): String = context.getString(R.string.loremIpsumLong)
fun getKeyStoryInfo(context: Context): String = context.getString(R.string.key_storyInfo)
fun getKeyStoriesByGenre(context: Context): String = context.getString(R.string.key_storiesByGenre)
fun getKeyIsText(context: Context): String = context.getString(R.string.key_isText)
fun getKeyGenreInfo(context: Context): String = context.getString(R.string.key_genreInfo)
fun getKeyUserInfo(context: Context): String = context.getString(R.string.key_userInfo)

fun getKeyPreviousChapterInfo(context: Context): String =
    context.getString(R.string.key_previousChapterInfo)

fun getKeyNextChapterInfo(context: Context): String =
    context.getString(R.string.key_nextChapterInfo)

fun getKeyMainChapterInfo(context: Context): String =
    context.getString(R.string.key_mainChapterInfo)

fun getKeyResultSearchInfo(context: Context): String =
    context.getString(R.string.key_resultSearchInfo)

fun getKeyTextQuery(context: Context): String = context.getString(R.string.key_textQuery)
fun getKeyMainChapter(context: Context): String {
    return context.resources.getString(R.string.key_mainChapterInfo)
}

fun getKeyNextChapter(context: Context): String {
    return context.resources.getString(R.string.key_nextChapterInfo)
}

fun getKeyPreviousChapter(context: Context): String {
    return context.resources.getString(R.string.key_previousChapterInfo)
}

fun getKeyChapterInfo(context: Context): String {
    return context.resources.getString(R.string.key_chapterInfo)
}

fun getImageAvatar(context: Context): Int = R.drawable.cat
fun getLoremIpsum(): String {
    return "lorem Ipsum"
}

fun getLoremIpsumLong(): String {
    return "Lorem ipsum odor amet, consectetuer adipiscing elit. Ullamcorper scelerisque vivamus leo pharetra inceptos litora vel cubilia himenaeos? Mi cras velit a dapibus rutrum nec imperdiet venenatis. Egestas accumsan inceptos aenean inceptos fringilla tortor facilisi et. Nisi ultrices ornare ex id pellentesque tristique magna ullamcorper. Feugiat massa nisi vivamus morbi platea pellentesque vehicula tellus. Dolor parturient fermentum nascetur, volutpat pretium fringilla."
}

fun getTextDataNotFound(context: Context): String {
    return context.resources.getString(R.string.dataNotFound)
}

fun callLog(name: String, text: String) {
    Log.w(name, text)
}

fun dateTimeNow(): String {
    return java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date())
}

fun dateNow(): String {
    return java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date())
}

fun timeNow(): String {
    return java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date())
}

//--------------------------------------
fun Int.toBoolean(): Boolean = this == 1
fun Boolean.toInt(): Int {
    return when (this) {
        true -> 1
        else -> 0
    }
}

fun ImageView.loadImgURL(context: Context, imageURL: String) {
    Picasso.get().load(imageURL).placeholder(R.drawable.icon_loading)
        .error(R.drawable.icon_error_404).into(this)
//    Glide.with(context).load(imageURL).placeholder(R.drawable.icon_loading)
//        .error(R.drawable.icon_error_404).into(this)

}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun ScrollView.scrollToBottom() {
    post {
        fullScroll(View.FOCUS_DOWN)
    }
}

fun View.hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.changeShapeBackgroundColorByScore(score: Float) {
    if (score >= 4f) {
        this.setBackgroundResource(R.drawable.shape_green_story_item_layout)

    } else if (score >= 2.5f && score < 4f) {
        this.setBackgroundResource(R.drawable.shape_yellow_card_story_item_layout)

    } else {
        this.setBackgroundResource(R.drawable.shape_red_card_story_item_layout)

    }
}

fun View.changeBackgroundTintColorByScore(score: Float) {
    var drawable = background
    val color = when {
        score >= 4f -> R.color.green2
        score >= 2.5f -> R.color.duc_yellow
        else -> R.color.duc_red
    }
    val colorFilter = context.getColor(color)

    drawable.setColorFilter(colorFilter, PorterDuff.Mode.SRC_ATOP)
}

fun Context.toActivity(activityClass: Class<out Activity>) {
    var intent = Intent(this, activityClass)
    this.startActivity(intent)
}

fun Context.toActivity(activityClass: Class<out Activity>, key: String, bool: Boolean) {
    var intent = Intent(this, activityClass)
    intent.putExtra(key, bool)
    this.startActivity(intent)
}

fun Context.toActivity(activityClass: Class<out Activity>, key: Int, value: Parcelable?) {
    var intent = Intent(this, activityClass)
    intent.putExtra(this.resources.getString(key), value)
    this.startActivity(intent)
}

fun Context.toActivity(activityClass: Class<out Activity>, key: String, value: Parcelable?) {
    var intent = Intent(this, activityClass)
    intent.putExtra(key, value)
    this.startActivity(intent)
}

fun Context.toActivity(activityClass: Class<out Activity>, key: Int, value: Serializable?) {
    var intent = Intent(this, activityClass)
    intent.putExtra(this.resources.getString(key), value)
    this.startActivity(intent)
}

fun Context.toActivity(activityClass: Class<out Activity>, key: Int, value: Bundle?) {
    var intent = Intent(this, activityClass)
    intent.putExtra(this.resources.getString(key), value)
    this.startActivity(intent)
}

fun Context.toActivity(activityClass: Class<out Activity>, key: String, value: Serializable?) {
    var intent = Intent(this, activityClass)
    intent.putExtra(key, value)
    this.startActivity(intent)
}

fun Context.toActivity(activityClass: Class<out Activity>, key: String, value: Bundle?) {
    var intent = Intent(this, activityClass)
    intent.putExtra(key, value)
    this.startActivity(intent)
}

fun showTestToast(context: Context) {
    Toast.makeText(context, "oke", Toast.LENGTH_SHORT).show()
}

fun showTestToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun showTestToastLong(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun createGridCardViewStory(
    context: Context,
    inflater: LayoutInflater,
    viewGroup: ViewGroup, genre: Genre,
    dataList: List<Story>,
    isText: Boolean
) {

    var blistCardStoriesLayout = ListCardStoriesLayoutBinding.inflate(inflater)
    val listCardStoriesLayout = blistCardStoriesLayout.root
    var gridLayout = blistCardStoriesLayout.gridLayoutListCardStory
    var txtGenre = blistCardStoriesLayout.genreListCardStory
    var btnSeeMore = blistCardStoriesLayout.txtSeeMoreListCardStory
    for (i in dataList) {
        var bCardView = CardStoryItemLayoutBinding.inflate(inflater)
        var cardView = bCardView.root
        var title = bCardView.txtTitleCardStoryItemLayout
        var author = bCardView.txtAuthorCardStoryItemLayout
        var imgURL = bCardView.imgCardStoryItemLayout
        var score = bCardView.txtRankCardStoryItemLayout
        var idStory = bCardView.idStoryCardStoryItem
        var constraintLayout = bCardView.constraintLayoutCardStoryLayout
        title.text = i.title
        author.text = i.author
        imgURL.loadImgURL(context, i.imgUrl)

        score.text = formatFloat(i.score)
        idStory.text = i.storyID.toString()
        constraintLayout.changeShapeBackgroundColorByScore(i.score)
        cardView.setOnClickListener({
            // truyen mot dataclass den activity moi
            context.toActivity(
                DucStoryOverviewActivity::class.java,
                context.getString(R.string.key_storyInfo),
                i
            )

            //   .toActivity(StoryOverviewActivity::class.java, R.string.key_storyInfo,i)
        })
        cardView.apply {
            layoutParams = GridLayout.LayoutParams().apply {
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f) // layout_columnWeight="1"
                setGravity(Gravity.CENTER)
                setMargins(0, 0, 0, 10.dpToPx())

            }
        }


        gridLayout.addView(cardView)

    }

    txtGenre.text = genre.genreName
    //chuyen den  Storise by Genre
    btnSeeMore.setOnClickListener {
        context.toActivityStoriesByGenre(isText, genre)
    }
    viewGroup.addView(listCardStoriesLayout)
    //return listCardStoriesLayout
}


fun Context.toActivityStoriesByGenre(isText: Boolean, genre: Genre) {
    var keyIsText = getKeyIsText(this)
    var keyGenreInfo = getKeyGenreInfo(this)
    var bundle = Bundle()
    bundle.putBoolean(keyIsText, isText)
    bundle.putParcelable(keyGenreInfo, genre)
    toActivity(DucStoriesByGenreActivity::class.java, getKeyStoriesByGenre(this), bundle)
}

fun getExampleGenre(context: Context): Genre {
    var title = context.getString(R.string.dataNotFound)
    return Genre(1, title, 1)
}

fun getExampleComicParagraph(context: Context): DucParagraphDataClass {
    return DucParagraphDataClass(
        1, SampleDataStory.getExampleImgURLParagraph(), context.getString(R.string.loremIpsum),
        1, 1, true
    )
}

fun getExampleTextParagraph(context: Context): DucParagraphDataClass {
    return DucParagraphDataClass(
        1, SampleDataStory.getExampleImgURLParagraph(), context.getString(R.string.loremIpsum),
        1, 1, false
    )
}

fun getExampleComicStory(context: Context): DucStoryDataClass {
    return DucStoryDataClass(
        1,
        context.getString(R.string.dataNotFound),
        context.getString(R.string.dataNotFound),
        context.getString(R.string.loremIpsum),
        SampleDataStory.getExampleImgURL(),
        SampleDataStory.getExampleImgURL(),
        context.getString(R.string.loremIpsum),
        4f,
        true
    )
}

fun getExampleTextStory(context: Context): DucStoryDataClass {
    return DucStoryDataClass(
        1,
        context.getString(R.string.dataNotFound),
        context.getString(R.string.dataNotFound),
        context.getString(R.string.loremIpsum),
        SampleDataStory.getExampleImgURL(),
        SampleDataStory.getExampleImgURL(),
        context.getString(R.string.loremIpsum),
        4f,
        false
    )
}

fun Context.getUserIdSession(): Int {
    var sharePref = getSharedPreferences(getString(R.string.key_user_session), Context.MODE_PRIVATE)
    val userId = sharePref.getInt(getString(R.string.key_user_id_session), -1)
    return userId

}

fun Context.getRoleGenreSession(): String {
    var sharePref = getSharedPreferences(getString(R.string.key_user_session), Context.MODE_PRIVATE)
    val role = sharePref.getString(getString(R.string.key_user_role_session), RoleEnum.GUEST.name)
    return role ?: RoleEnum.GUEST.name

}

fun Context.isUserCurrentGuest(): Boolean {

    return getRoleGenreSession() == RoleEnum.GUEST.name
}

fun Context.isUserCurrentAdmin(): Boolean {
    return getRoleGenreSession() == RoleEnum.ADMIN.name

}

fun Context.isUserCurrentAuthor(): Boolean {
    return getRoleGenreSession() == RoleEnum.AUTHOR.name

}

fun Context.isUserCurrentMember(): Boolean {
    return getRoleGenreSession() == RoleEnum.MEMBER.name

}

fun Context.clearUserSession() {
    val sharedPref =
        getSharedPreferences(getString(R.string.key_user_session), Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        clear()
        apply()
    }
}

fun hashPassword(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt())
}

fun checkHashPassword(rawPassword: String, hashedPassword: String): Boolean {
    return BCrypt.checkpw(rawPassword, hashedPassword)
}

fun formatFloat(num: Float): String {
    return String.format("%.1f", num)
}

fun String.dateFromDateTime(): String {
    return split(" ")[0]
}