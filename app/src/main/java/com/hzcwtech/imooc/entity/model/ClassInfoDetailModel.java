package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/23.
 */

public class ClassInfoDetailModel implements Serializable {
    private static final long serialVersionUID = -6108099238701567655L;

    /**
     * evaluate : {"commentcount":"157","consult":"这是有时间限制的吗？","evaluate":[{"comment_score":"10.00","content":"很好的，感觉很适合入门，之前没有接触过前端，认真听完之后，基本的前端就可以做出来了。课程配了助教，提问之后助教老师都会及时的回复，而且感觉特别棒的地方是作业，学完一块之后就会有布置相应的作业，作业提交之后会有老师批改打分，批改的特别认真，在课程介绍里面有一个蓝绿色主题的页面，那个就是作业，在我提交的时候，有一个闭合标签忘记删除了，但是不影响整体外观，老师在批改的时候都给我指出来了。","create_time":"1495298523","img":"http://img.mukewang.com/user/5458634b0001120b02200220-100-100.jpg","nickname":"Danci_cc","uid":"3179941","comment_sco4care":"10.00"},{"comment_sco4care":"10.00","content":"不听不知道，一听吓一跳，自己的基础真是差呀!本来第一次学的是html5与css3实现动态网页,但是中途发现自己基础薄弱，于是再次缴费这个课程，这次想好好的把基础打牢，这个课程很清晰，也很容易懂，特别适合小白入门，感觉还是非常满意的，继续努力加油!期待慕课网的js进阶课程尽快出来，然后学完找个好工作，就大功告成了!嘻嘻","create_time":"1494997348","img":"http://img.mukewang.com/user/591284b80001672606400640-100-100.jpg","nickname":"ZJDreaming","uid":"1990604"}],"score":"9.97","weidu":[{"id":1,"score":"10.0","sort":1,"title":"内容实用"},{"id":2,"score":"10.0","sort":2,"title":"通俗易懂"},{"id":3,"score":"9.9","sort":3,"title":"逻辑清晰"}]}
     * info : {"comment_score":"9.97","commentcount":"157","coursecount":"33","id":"20","is_buy":0,"name":"前端小白入门","numbers":"925","pic":"http://climg.mukewang.com/59030cc50001144806000338-300-170.jpg","price":499,"share":"http://class.imooc.com/sc/20","short_description":"从一个不会编程的小白到一个老司机是需要过程的，首先得入门，学习基础知识，然后才能进阶，最后再到精通，本专题是你走进前端世界的不二选择！","tip":"","total_time":"52"}
     * pic_info : ["http://climg.mukewang.com/59030e9d000112e607501204.jpg","http://climg.mukewang.com/59030ed300018b5007501012.jpg","http://climg.mukewang.com/58bfb3f20001f06607502615.jpg"]
     * steplist : [{"courselist":[{"course_time":"课程时长： 1小时59分","name":"HTML基础","short_description":"HTML是网页制作必备技能，在本课程主要介绍HTML概念、语法及常用基础标签","type":"1","type_id":"191"},{"course_time":"课程时长： 1小时26分","name":"HTML表格","short_description":"表格在网页中用于数据和排版，本课程介绍表格概念、语法、操作，并通过案例掌握知识。","type":"1","type_id":"194"},{"course_time":"课程时长： 1小时37分","name":"HTML表单","short_description":"表单用于收集用户信息，本课程介绍表单概念、语法及创建表单，并通过案例掌握知识。","type":"1","type_id":"193"},{"course_time":"课程时长： 0小时47分","name":"案例搭建网页HTML结构","short_description":"本课程带领大家一起搭建一个网页HTML结构，并掌握网布局相应知识与技巧。","type":"1","type_id":"173"},{"course_time":"测评题","exam_frequency":2,"exam_num":"20","name":"HTML基础测试","paper_time":"30","short_description":"HTML基础测试","title":"HTML基础测试","type":"2","type_id":"41"}],"name":"HTML基础","seqid":"1"},{"courselist":[{"course_time":"课程时长： 2小时27分","name":"CSS选择的艺术","short_description":"本课程主要介绍CSS语法、选择器、继承、层叠及优先级基础内容。","type":"1","type_id":"184"},{"course_time":"课程时长： 1小时38分","name":"CSS文本样式","short_description":"CSS为字体、文本提供了大量的样式属性，可以使我们的页面式更加丰富的多彩。","type":"1","type_id":"185"},{"course_time":"课程时长： 1小时1分","name":"CSS背景和列表","short_description":"本课程带领大家了解一下背景颜色、背景图片的相关知识，以及列表的多种样式。","type":"1","type_id":"187"},{"course_time":"课程时长： 1小时34分","name":"盒子模型","short_description":"我们就来学习一下css的盒子模型，理解了盒子模型，才能更好的排版，进行页面布局。","type":"1","type_id":"188"},{"course_time":"课程时长： 0小时36分","name":"float浮动","short_description":"本课程主要介绍了浮动的作用，带领大家用更好的方式来布局页面。","type":"1","type_id":"189"},{"course_time":"课程时长： 1小时20分","name":"CSS定位（position）","short_description":"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。","type":"1","type_id":"190"},{"course_time":"课程时长： 0小时57分","name":"CSS网页布局基础","short_description":"本课程，我们我们就针对CSS中的基础布局以及经典的布局展开详细的讲解。","type":"1","type_id":"192"},{"course_time":"课程时长： 0小时51分","name":"网页布局案例","short_description":"本课程通过实战的讲解，带领大家体验真实开发中的的布局样式。","type":"1","type_id":"195"},{"course_time":"测评题","exam_frequency":2,"exam_num":"20","name":"CSS基础测试","paper_time":"30","short_description":"CSS基础测试","title":"CSS基础测试","type":"2","type_id":"48"}],"name":"CSS基础","seqid":"2"},{"courselist":[{"course_time":"课程时长： 1小时46分","name":"JavaScript语法","short_description":"初步了解JavaScript语言，掌握它的语法、数据类型、基本算数和逻辑运算操作","type":"1","type_id":"206"},{"course_time":"课程时长： 1小时24分","name":"JavaScript流程控制语句","short_description":"掌握JavaScript中条件分支语句和循环语句的使用，用简洁的代码实现强大功能","type":"1","type_id":"203"},{"course_time":"课程时长： 0小时36分","name":"JavaScript函数","short_description":"掌握函数的使用，学习函数的封装，体会代码复用的过程和它带来的便利","type":"1","type_id":"204"},{"course_time":"课程时长： 2小时48分","name":"JavaScript内置对象","short_description":"学习内置对象的常用属性和方法，方便我们开发中直接调用，进而实现更多功能","type":"1","type_id":"205"},{"course_time":"课程时长： 0小时46分","name":"JavaScript DOM基础","short_description":"DOM的方法和属性既可以获取网页中的元素，也可以设置元素的内容、样式及效果","type":"1","type_id":"211"},{"course_time":"课程时长： 1小时34分","name":"JavaScript DOM事件","short_description":"为页面中的元素绑定键盘或鼠标事件，从而可以触发和实现我们想要的交互效果","type":"1","type_id":"202"},{"course_time":"课程时长： 1小时40分","name":"JavaScript BOM基础","short_description":"学习浏览器对象模型\u201cBOM\u201d，可以对浏览器窗口进行访问和操作，与浏览器\u201c对话\u201d","type":"1","type_id":"207"},{"course_time":"课程时长： 2小时40分","name":"JavaScript实现轮播特效","short_description":"综合运用JavaScript知识，做出轮播图、tab页切换等实用特效","type":"1","type_id":"200"},{"course_time":"测评题","exam_frequency":2,"exam_num":"25","name":"JavaScript基础测试","paper_time":"37","short_description":"JavaScript基础测试","title":"JavaScript基础测试","type":"2","type_id":"45"}],"name":"JavaScript基础","seqid":"3"},{"courselist":[{"course_time":"课程时长： 1小时43分","name":"jQuery选择的艺术","short_description":"课程主要介绍jQuery的概念、版本、选择器，筛选器、以及与js的区别。","type":"1","type_id":"174"},{"course_time":"课程时长： 3小时11分","name":"jQuery DOM操作","short_description":"本课程主要讲解jQuery中查找元素、修改元素样式和内容以及其它操作方法。","type":"1","type_id":"182"},{"course_time":"课程时长： 1小时3分","name":"jQuery事件","short_description":"本课程主要介绍了jQuery对象中的0级事件、2级事件，以及事件的添加、移除、触发、自定义事件、命名空间。","type":"1","type_id":"183"},{"course_time":"课程时长： 0小时54分","name":"jQuery插件","short_description":"本课程主要介绍了如何寻找自己需要的插件，如何使用插件，几个常用插件的介绍、以及如何自定义插件。","type":"1","type_id":"196"},{"course_time":"课程时长： 0小时43分","name":"jQuery弹出层案例","short_description":"通过弹出层案例的讲解，让大家更灵活的使用jQuery的属性和方法以及函数的封装。","type":"1","type_id":"198"},{"course_time":"课程时长： 1小时47分","name":"瀑布流布局案例","short_description":"瀑布流布局，是目前比较流行的一种网页布局方式，它会随着页面滚动条的滚动而不断加载内容。","type":"1","type_id":"199"},{"course_time":"测评题","exam_frequency":2,"exam_num":"20","name":"jQuery基础测试","paper_time":"30","short_description":"jQuery基础测试","title":"jQuery基础测试","type":"2","type_id":"44"}],"name":"jQuery基础","seqid":"4"},{"courselist":[{"course_time":"课程时长： 0小时39分","name":"Photoshop辅助工具","short_description":"Photoshop是图像处理的软件，本课程主要介绍了它是如何对图形图像进行编辑和色彩处理的。","type":"1","type_id":"186"},{"course_time":"课程时长： 4小时11分","name":"统一挂号平台案例","short_description":"结合所学的HTML、CSS、jQuery知识，完成整站开发","type":"1","type_id":"197"},{"course_time":"测评题","exam_frequency":2,"exam_num":"20","name":"实战测试","paper_time":"30","short_description":"实战测试","title":"实战测试","type":"2","type_id":"47"}],"name":"实战案例","seqid":"5"}]
     */

    private EvaluateDetailModel evaluate;
    private ClassInfoSummaryModel info;
    private List<String> pic_info;
    private List<StepModel> steplist;

    public EvaluateDetailModel getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(EvaluateDetailModel evaluate) {
        this.evaluate = evaluate;
    }

    public ClassInfoSummaryModel getInfo() {
        return info;
    }

    public void setInfo(ClassInfoSummaryModel info) {
        this.info = info;
    }

    public List<String> getPic_info() {
        return pic_info;
    }

    public void setPic_info(List<String> pic_info) {
        this.pic_info = pic_info;
    }

    public List<StepModel> getSteplist() {
        return steplist;
    }

    public void setSteplist(List<StepModel> steplist) {
        this.steplist = steplist;
    }
}
