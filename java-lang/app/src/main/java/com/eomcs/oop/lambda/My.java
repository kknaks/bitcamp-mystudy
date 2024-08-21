// package study.oop.lambda;
//
// public class My implements InterestCalculator {
// double rate;
//
// public My(double rate) {
// this.rate = rate;
// }
//
// public double comput(int money) {
// return money * (1 + rate);
// }
// }
//
// interface InterestCalculator {
// double compute(int money);
// }
//
//
// public class Factory {
// static InterestCalculator create(double rate) {
// return money -> money * (1 + rate);
// }
// }
//
//// public class Factory {
//// static InterestCalculator create(double rate) {
//// return (money)->money*(1+rate);
//// }
//// }
//
//// public class Factory {
//// static InterestCalculator create(double rate) {
//// return (int money)->{return money*(1+rate);};
//// }
//// }
//
//// public class Factory {
//// static InterestCalculator create(double rate) {
//// return new InterestCalculator() {
//// @Override
//// public double compute(int money) {
//// return money * (1 + rate);
//// }
//// };
//// }
//
//// public class Factory {
//// static InterestCalculator create(double rate) {
//// InterestCalculator c1 = new InterestCalculator() {
//// @Override
//// public double compute(int money) {
//// return money * (1 + rate);
//// }
//// };
//// return c1;
//// }
//// }
//
//// public class Factory {
//// static InterestCalculator create(double rate) {
//// class My implements InterestCalculator {
//// @Override
//// public double compute(int money) {
//// return money * (1 + rate);
//// }
//// }
////
//// return new My();
//// }
//// }
//
//// public class Factory {
//// static InterestCalculator create(double rate) {
//// class My implements InterestCalculator {
//// double rate;
////
//// public My(double rate) {
//// this.rate = rate;
//// }
////
//// @Override
//// public double compute(int money) {
//// return money * (1 + rate);
//// }
//// }
////
//// return new My(rate);
//// }
//// }
