 var mainDiv = document.getElementById('maindiv');
 var startDiv = document.getElementById('startdiv');
 var scoreDiv = document.getElementById('scorediv');
 var pauseDiv = document.getElementById('pausediv');
 var label = document.getElementById('label');
 var endDiv = document.getElementById('enddiv');
 var endlabel = document.getElementById('planescore');
 var mark = 0,mark1 = 0;
 var enemies = [],bullets = [];
 var scores = 0;
 var dieTime = 300;
 var sid ;
 var j = 0;
 // function Music(){
 // var music = document.createElement('audio');
 // music.src = 'x.mp3';
 //document.body.appendChild(audio);}

 <!--点击鼠标左键暂停游戏，右键弹选择框-->
  function mouseDownAction(){
  	document.oncontextmenu = function(){
	var event = event || window.event;
	pauseDiv.style.display = 'block';
    return false;
}
    if(document.removeEventListener){
		mainDiv.removeEventListener('mousemove',ourPlaneMove,true);
		mainDiv.removeEventListener('mousemove',ourPlaneMoveOut,true);
		}
		else if(document.detachEvent){
            mainDiv.detachEvent('onmousemove',ourPlaneMove);
           	mainDiv.detachEvent('onmousemove',ourPlaneMoveOut);
		}
		clearInterval(sid); //停止定时器
}

<!--选择弹框选项——继续游戏 -->
function again(){
 	startDiv.style.display = 'none';
	mainDiv.style.display = 'block';
	scoreDiv.style.display = 'block';
	pauseDiv.style.display = 'none';
	endDiv.style.display = 'none';
	sid = setInterval(start,30);
	if(document.addEventListener){
	mainDiv.addEventListener('mousemove',ourPlaneMove,true);
	mainDiv.addEventListener('mousemove',ourPlaneMoveOut,true);
	}else if(document.attachEvent){
    mainDiv.attachEvent('onmousemove',ourPlaneMove);
    mainDiv.attachEvent('onmousemove',ourPlaneMoveOut);
    }     //我方飞机有两次复活机会，在复活后的分数累加是在死亡之前的得分上减去相应得分
    if(ourplane.planeIsDie == true && j <= 2){
    	scores = scores - 100 * j; 
    	ourplane.planeIsDie = false;
    	}else if( j >2){
    		ourplane.planeIsDie = false;
    		endDiv.style.display = 'block';
			endlabel.innerHTML = scores;
			if(document.removeEventListener){
			mainDiv.removeEventListener('mousemove',ourPlaneMove,true);
			mainDiv.removeEventListener('mousemove',ourPlaneMoveOut,true);
			}
			else if(document.detachEvent){
           	mainDiv.detachEvent('onmousemove',ourPlaneMove);
           	mainDiv.detachEvent('onmousemove',ourPlaneMoveOut);
			}
			clearInterval(sid);
    	}
}

 <!--开始游戏 -->
 function begin(){
 	startDiv.style.display = 'none';
	mainDiv.style.display = 'block';
	scoreDiv.style.display = 'block';
	sid = setInterval(start,30);
	document.onmousedown = mouseDownAction;
  }

<!--继续游戏 -->
function goOn(){
	location.reload(true);
}

<!--添加一个图片节点对象-->
function init(){
	this.imageNode = document.createElement('img');
	this.imageNode.style.position = 'absolute';
    this.imageNode.style.left = this.x + 'px' ;
    this.imageNode.style.top = this.y +'px' ;
    this.imageNode.src= this.imgSrc ;
    mainDiv.appendChild(this.imageNode);//将图片对象放入mainDiv
}
<!-- 创建飞机对象-->
function Plane(x,y,imgSrc,width,height,speed,hp,score,boomImageSrc){
	this.x = x;
	this.y = y;
	this.imgSrc = imgSrc;
	this.width = width;
	this.height = height;
	this.init = init;
	this.init();
	this.move = enemyMove;
	this.speed = speed;
	this.hp = hp;
	this.score = score;
	this.boomImageSrc = boomImageSrc;
	this.planeIsDie = false;
	this.planeDieTime = 0;
}

<!--创建我方飞机 -->
function OurPlane(x,y) {
	//让THIS对象调用plane 方法。将plane函数中this的指针指向当前的THIS
	Plane.call(this,x,y,'image/我的飞机.gif',66,80,0,1,0,'image/本方飞机爆炸.gif');
    this.imageNode.setAttribute('id','ourplane');
}
var ourplane = new OurPlane(120,485);//创建自己的飞机对象
 
<!--鼠标移动，我方飞机移动-->
function ourPlaneMove() {
	var event = window.event || arguments[0];
	var selfPlaneX = event.clientX - 500;
	var selfPlaneY = event.clientY;
	ourplane.imageNode.style.left = selfPlaneX  - ourplane.width / 2 + 'px';
	ourplane.imageNode.style.top = selfPlaneY  - ourplane.height/ 2 + 'px';
}

<!--限定我方飞机的移动范围-->
function ourPlaneMoveOut() { <!--方法的参数列表-->
	var event = window.event || arguments[0];//浏览器兼容
	      //获取到当前对象
	var movePlaneX = event.clientX;
	var movePlaneY = event.clientY;
	if(movePlaneX < 510 || movePlaneX > 800 || movePlaneY < 0 || movePlaneY > 568){
		// 飞机偏移出背景Div，就移除鼠标移动事件
		if(document.removeEventListener){
			mainDiv.removeEventListener('mousemove',ourPlaneMove,true);
		}else if(document.detachEvent){
            mainDiv.detachEvent('onmousemove',ourPlaneMove);
		}
	}
	else{
			if(document.addEventListener){
				mainDiv.addEventListener('mousemove',ourPlaneMove,true);
			}
           	else if(document.attachEvent){
     			mainDiv.attachEvent('onmousemove',ourPlaneMove);
     		}

		}
}

<!--给我方飞机添加移动事件-->
if(document.addEventListener){
	mainDiv.addEventListener('mousemove',ourPlaneMove,true);
	mainDiv.addEventListener('mousemove',ourPlaneMoveOut,true);
}else if(document.attachEvent){
    mainDiv.attachEvent('onmousemove',ourPlaneMove);
    mainDiv.attachEvent('onmousemove',ourPlaneMoveOut);

}

<!--创建敌方飞机-->
function Enemy(a,b,sizeX,sizeY,imgSrc,speed,hp,score,boomImageSrc) {
	Plane.call(this,Random(a,b),-10,imgSrc,sizeX,sizeY,speed,hp,score,boomImageSrc);
}
function Random(min,max){//敌方飞机坐标随机产生
	var number = Math.random();
	return Math.floor(min + number *(max - min));

}
<!--敌方飞机的移动 -->
function enemyMove(){
	if(scores <= 500){
	       this.imageNode.style.top = this.imageNode.offsetTop + this.speed + 'px';
	   }else if(scores > 500 && scores<= 1000 ){
	   	   this.imageNode.style.top = this.imageNode.offsetTop + this.speed + 5 +'px';
	   }else if(scores>1000 && scores<= 3000){
	  	   this.imageNode.style.top = this.imageNode.offsetTop + this.speed + 7 +'px';
	   }else if(scores>3000 && scores<= 6000){
           this.imageNode.style.top = this.imageNode.offsetTop + this.speed + 13 +'px';
	   }else{
	  	   this.imageNode.style.top = this.imageNode.offsetTop + this.speed + 18 +'px';
	   }
}

<!--添加一个新节点用于存放子弹图片-->
function bulletInit() {
	this.bulletImage = document.createElement('img');
	this.bulletImage.style.position = 'absolute';
    this.bulletImage.style.left = this.x + 'px' ;
    this.bulletImage.style.top = this.y + 'px' ;
    this.bulletImage.src= this.imgSrc ;
    mainDiv.appendChild(this.bulletImage);
}
 <!-- 创建子弹对象-->
function Bullet(x,y,sizeX,sizeY,imgSrc) {
	this.x = x;
	this.y = y;
	this.sizeX = sizeX;
	this.sizeY = sizeY;
	this.imgSrc = imgSrc;
	this.init = bulletInit;
	this.init();
	this.move = bulletMove;
}
<!--子弹由图片形式呈现-->
function OurPlaneBullet(x,y) {
	 Bullet.call(this,x,y,4,10,'image/bullet1.png');
 }
<!--子弹向上移动-->
function bulletMove(x,y) {
	this.bulletImage.style.top = this.bulletImage.offsetTop - 20 + 'px';//每次Y的变化为减20
}


function start(){ //实现子弹随飞机而动
	mark++;
	if(mark % 5 == 0){
		mark1++;  //敌方飞机有大小
		if(mark1 % 6 == 0){//产生中型飞机
			var enemy = new Enemy(25,270,46,60,'image/enemy3_fly_1.png',4,3,50,'image/中飞机爆炸.gif');
			enemies.push(enemy);
		}else if(mark1 == 41){//产生大型飞机
			var enemy = new Enemy(57,210,110,160,'image/enemy2_fly_1.png',3,8,100,'image/大飞机爆炸.gif');
			enemies.push(enemy);
			mark1 = 0;
		}else if(mark1 % 2 == 0){
			var enemy = new Enemy(19,290,34,24,'image/enemy1_fly_1.png',5,1,10,'image/小飞机爆炸.gif');
			enemies.push(enemy);
		}

//传入子弹横纵坐标，其横纵坐标与飞机坐标相关
	var planeX,planeY; 
	var bullet = new OurPlaneBullet(planeX,planeY);
	bullets.push(bullet);
	if(scores <= 500 ){
		bullets.push(new OurPlaneBullet(parseInt(ourplane.imageNode.style.left)+ourplane.width/2 ,parseInt(ourplane.imageNode.style.top)-5));
	}
	if(scores > 500 && scores<= 2500 ){
	bullets.push(new OurPlaneBullet(parseInt(ourplane.imageNode.style.left)+ourplane.width/2 - 20,parseInt(ourplane.imageNode.style.top)+10));
	bullets.push(new OurPlaneBullet(parseInt(ourplane.imageNode.style.left)+ourplane.width/2 + 20,parseInt(ourplane.imageNode.style.top)+10));
    }
   if(scores > 2500 ){
   	bullets.push(new OurPlaneBullet(parseInt(ourplane.imageNode.style.left)+ourplane.width/2 ,parseInt(ourplane.imageNode.style.top)-5));
	bullets.push(new OurPlaneBullet(parseInt(ourplane.imageNode.style.left)+ourplane.width/2 - 20,parseInt(ourplane.imageNode.style.top)+10));
	bullets.push(new OurPlaneBullet(parseInt(ourplane.imageNode.style.left)+ourplane.width/2 + 20,parseInt(ourplane.imageNode.style.top)+10));
   }
  }

//发射子弹，
	for(var i = 0; i < bullets.length; i++){
	   if(bullets[i] != 'undefined'){
		var bulletIndex = bullets[i];
		 bulletIndex.move();
	    }
	      if(bulletIndex.bulletImage.offsetTop < 0 ){//子弹从mainDiv移除
		    mainDiv.removeChild(bulletIndex.bulletImage);
		    bullets.splice(i,1);
	      }
    }

//敌方飞机下落
	for(var index = 0; index < enemies.length; index++){ 
	       var enemyIndex = enemies[index];
	       if(enemyIndex.planeIsDie == false){
	      	enemyIndex.move();
	       }
//飞机移出屏幕，销毁飞机
	     	if(enemyIndex.imageNode.offsetTop > 568){
	     		mainDiv.removeChild(enemyIndex.imageNode);
	     		enemies.splice(index,1);
	     	}
//敌方飞机血量为零时爆炸并发生延时消失
         if(enemyIndex.planeIsDie == true){
         	enemyIndex.planeDieTime += 20;
         	if(enemyIndex.planeDieTime > dieTime){
         		mainDiv.removeChild(enemyIndex.imageNode);
         		enemies.splice(index,1);
         	}
         }
	}
//遍历子弹和飞机数组
	for(var i = 0; i< bullets.length; i++){
		var bulletIndex = bullets[i];
		for(var index = 0; index< enemies.length; index++){
			var enemyIndex = enemies[index];
			if(enemyIndex.planeIsDie == false &&  ourplane.planeIsDie == false){    //先判断敌机是否死亡,检测碰撞，更改我方飞机爆炸图片，游戏结束，移除事件
				if(enemyIndex.imageNode.offsetLeft + enemyIndex.width >= ourplane.imageNode.offsetLeft+10 
					&&  enemyIndex.imageNode.offsetLeft <= ourplane.imageNode.offsetLeft +ourplane.width -10
					&& enemyIndex.imageNode.offsetTop <= ourplane.imageNode.offsetTop + ourplane.height-30 
					&& enemyIndex.imageNode.offsetTop + enemyIndex.height  >= ourplane.imageNode.offsetTop +30){ //飞机和敌机的碰撞
						ourplane.imageNode.src = ourplane.boomImageSrc;
					j++;
					    ourplane.planeIsDie = true;
						endDiv.style.display = 'block';
						endlabel.innerHTML = scores;
						if(document.removeEventListener){
							mainDiv.removeEventListener('mousemove',ourPlaneMove,true);
							mainDiv.removeEventListener('mousemove',ourPlaneMoveOut,true);
						}
							else if(document.detachEvent){
           					 mainDiv.detachEvent('onmousemove',ourPlaneMove);
           					 mainDiv.detachEvent('onmousemove',ourPlaneMoveOut);
							}
							clearInterval(sid); //停止定时器
				}
		//子弹和敌机发生碰撞
				if(enemyIndex.imageNode.offsetLeft + enemyIndex.width >= bulletIndex.bulletImage.offsetLeft 
					&&  enemyIndex.imageNode.offsetLeft <= bulletIndex.bulletImage.offsetLeft + bulletIndex.sizeX
					&& enemyIndex.imageNode.offsetTop <= bulletIndex.bulletImage.offsetTop + bulletIndex.sizeY 
					&& enemyIndex.imageNode.offsetTop + enemyIndex.height  >= bulletIndex.bulletImage.offsetTop){
					enemyIndex.hp = enemyIndex.hp -1;//计算血量
		//计算分数	
					if(enemyIndex.hp == 0){
						scores = scores + enemyIndex.score;
			    		label.innerHTML = scores;
						enemyIndex.imageNode.src = enemyIndex.boomImageSrc;
						enemyIndex.planeIsDie = true;
					}
		//消除子弹
					mainDiv.removeChild(bulletIndex.bulletImage);
					bullets.splice(i,1);
					break;
				}
		    }
		}
	}
}






