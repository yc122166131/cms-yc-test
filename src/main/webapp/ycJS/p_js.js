

/**
 * 获取入参的所有数字并且进行相加操作
 * 返回相加后的总和
 * 使用 var sum = sumOnlyNumbers(1,2,3,'tjq','ccc');
 * console.log(sum());
 * @returns {Function}
 */
function sumOnlyNumbers(){
	var args = arguments; 
	return function(){
		var numArray = Array.prototype.filter.call(args,function(elem){
			   return typeof elem === 'number'; 
		});
		
		return numArray.reduce(function(sum,num){
			return sum+ num;
		});
		
	}
}

