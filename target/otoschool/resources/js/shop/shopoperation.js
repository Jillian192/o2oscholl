
$(function () {
	 // debugger;
	var initUrl='/otoschool_war/shopadmin/getshopinitinfo';
	var registerShopUrl='/otoschool_war/shopadmin/registShop';
	alert(initUrl);
	getShopInitInfo();
	function getShopInitInfo() {
		$.getJSON(initUrl,function (data) {
			if (data.success) {
				var tempHtml="";
				var tempAreaHtml="";
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
						+ '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
						+ item.areaName + '</option>';
				});
				$('#shop_catgory').html(tempHtml);
				$('#shop_area').html(tempAreaHtml);
			}
		});
		$('#shop_submit').click(function () {
			var shop={};
			shop.shopName=$('#shop_name').val();
			shop.shopAddr=$('#shop_addr').val();
			shop.phone=$('#shop_phone').val();
			shop.shopDesc=$('#shop_desc').val();
			//店铺类别
			shop.shopCategory={
				shopCategoryId:$('#shop_catgory').find('option').not(function () {
					return !this.selected;
				}).data('id')
			}
			// 选择选定好的区域信息
			shop.area = {
				areaId : $('#shop_area').find('option').not(function() {
					return !this.selected;
				}).data('id')
			};
			// 获取上传的图片文件流
			var shopImg = $('#shop_sub_file')[0].files[0];
			// 生成表单对象，用于接收参数并传递给后台
			var formData = new FormData();
			var shopCaptcha=$('#shop_captcha').val();
			if (!shopCaptcha) {
				$.toast('请输入验证码');
				return;
			}

			formData.append('verifyCodeActual',shopCaptcha);

			// 添加图片流进表单对象里
			formData.append('shopImg', shopImg);
			// 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
			formData.append('shopStr', JSON.stringify(shop));
            $.ajax({
				url :registerShopUrl,
				type :'POST',
				data: formData,
				contentType:false,
				processData:false,
				cache:false,
				success:function (data) {
					if(data.success()){
						$.toast('提交成功');
					}else {
						$.toast('提交失败！'+data.errMsg);
					}
					$('#shop_captcha_img').click();
				}
			});
		})
	}
})