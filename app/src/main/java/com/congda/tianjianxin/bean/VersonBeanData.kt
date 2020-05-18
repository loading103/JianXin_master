
data class VersonBeanData (
    val version: String,
    val code: String,
    val downloadUrl: String,//下载方式:1=直接下载，2=内部跳转，3=外部跳转
    val downloadType: String,
    val isForce: String,
    val updateDesc: String
)