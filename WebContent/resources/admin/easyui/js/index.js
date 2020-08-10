var addBtn = $(".add-area .addBtn")[0];
console.log($);
var $options = $('.options')
var $deleteBtn = $(".options .deleteBtn");
// 给最开始的input  绑定事件
$deleteBtn.click(removeItem)

function removeItem() {
    // 删除父级  因为删除按钮和输入框 都是放在同一个父级下的
    $(this).parent().remove();
}

function getOptionItem() {
	var timestamp = Date.parse(new Date());
    var $optionItem = $(
        `<div class='option-item'>
    <input name="name" id="`+timestamp+`" value="A选项" type="text" />
    <div class="deleteBtn">删除</div>
        </div>`
    )
    // 给按钮绑定删除事件
    $optionItem.find(".deleteBtn").click(removeItem)

    return $optionItem;
}


// console.log($optionItem)
addBtn.onclick = function (e) {
    $options.append(getOptionItem()[0])

}