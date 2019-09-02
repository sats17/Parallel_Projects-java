//const reversedNum = num => parseFloat(num.toString().split('').reverse().join('')) * Math.sign(num)
// function reversedNum(num) {
//     return (
//       parseFloat(
//         num
//           .toString()
//           .split('')
//           .reverse()
//           .join('')
//       ) * Math.sign(num)
//     )                 
//   }
var n = 8498448
var y = n.toString()
var z = y.split("").reverse().join("");
var m = Number(z);
console.log(m)
