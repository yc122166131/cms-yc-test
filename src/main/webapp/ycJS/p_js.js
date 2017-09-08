

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




/**
 * 兼容不能使用filter 函数的浏览器
 * 比如 ie8 及 以下
 * call() 第一个参数 this 原则上说 是什么都可以 包括undefined (下面就是 undefined)
 */
if (!Array.prototype.filter)
{
  Array.prototype.filter = function(fun /*, thisArg */)
  {
    "use strict";
    
    if (this === void 0 || this === null)
      throw new TypeError();

    var t = Object(this);
    var len = t.length >>> 0;
    if (typeof fun !== "function")
      throw new TypeError();

    var res = [];
    var thisArg = arguments.length >= 2 ? arguments[1] : void 0;
    for (var i = 0; i < len; i++)
    {
      if (i in t)
      {
        var val = t[i];

        // NOTE: Technically this should Object.defineProperty at
        //       the next index, as push can be affected by
        //       properties on Object.prototype and Array.prototype.
        //       But that method's new, and collisions should be
        //       rare, so use the more-compatible alternative.
        if (fun.call(thisArg, val, i, t))
          res.push(val);
      }
    }

    return res;
  };
}



