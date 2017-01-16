配置信息参考

	/*
	############## excel解析配置 #################
	excel.file.name=party.xls
	excel.hasTitle=true
	excel.field.names.en=partyName,idCard,sex,education,workUnit,joinPartyTime,phoneNo,partyFee,partyBranchName,partyTeam,specialty
	unicode.split=,
	excel.field.names.cn=59d3,540d,002c,8eab,4efd,8bc1,53f7,7801,002c,6027,522b,002c,5b66,5386,002c,5de5,4f5c,5355,4f4d,002c,5165,515a,65f6,95f4,002c,8054,7cfb,7535,8bdd,002c,515a,8d39,002c,6240,5728,652f,90e8,002c,6240,5728,5c0f,7ec4,002c,4e2a,4eba,7279,957f
	#姓名,身份证号码,性别,学历,工作单位,入党时间,联系电话,党费,所在支部,所在小组,个人特长
	excel.field.not.nulls=0, 1, 2, 5, 6, 8
	excel.field.error.flag=$
	excel.field.regexp.prefix=excel.field.regexp.
	excel.field.regexp.idCard=^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$
	excel.field.regexp.phoneNo=(^(\\d{3,4}-)?\\d{7,8})$|(^[0-9]*\\d{11}$)
	excel.field.regexp.partyFee=^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$
	*/