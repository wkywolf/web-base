一、jqgrid的json格式：
	当jsonReader: {
						repeatitems: false
					}
	1. repeatitems:false时，json格式为：
	 {"page":"1","total":"2","records":"13",
		 "rows":[
	                 {id:"1",userName:"polaris",gender:" 男",email:"polaris@gmail.com",QQ:"772618379",mobilePhone:"18329382732",birthday:"1985-10-2"]},
	                 {id:"2",userName:"徐新华",gender:" 男",email:"xh.xu@163.com",QQ:"272618382",mobilePhone:"15329382732",birthday:"1986-10-12"]},
	                 {id:"3",userName:"王五",gender:" 女",email:"wangwu@yahoo.com",QQ:"172635372",mobilePhone:"13329389832",birthday:"1987-12-21"]},
	                 {id:"4",userName:"赵六",gender:" 女",email:"zhaoliu@sina.com",QQ:"372618332",mobilePhone:"18929343731",birthday:"1988-09-22"]}
	         	]
	}
	2. repeatitems:true时，json格式为：
	{"page":"1","total":"2","records":"13",
		"rows":[
	                {id:"1",cell:["1","polaris","男","polaris@gmail.com","772618379","18329382732","1985-10-2"]},
	                {id:"2",cell:["2","张三","女","zhangsan@163.com","272618382","15329382732","1986-10-12"]},
	                {id:"3",cell:["3","王五","女","wangwu@yahoo.com","172635372","13329389832","1987-12-21"]},
	                {id:"4",cell:["4","赵六","男","zhaoliu@sina.com","372618332","18929343731","1988-09-22"]}
		        ]
		}
		
		// 组装jqgrid对每行数据的操作
//		StringBuffer optSB = null;
//		if(users!=null && users.size()>0){
//			for(User u : users){
//				optSB = new StringBuffer();
//				optSB.append("<a title=\"修改\" href=\"${ctx}/user/update?id=" + u.getId() + "\">[修改]</a>");
//				optSB.append(" ");
//				optSB.append("<a title=\"删除\" href=\"javaScript:deleteUser('${ctx}/user/delete?id=" + u.getId() + "');\" id=\"delete\">[删除]</a>");
//				u.setOpt(optSB.toString());
//			}
//		}

