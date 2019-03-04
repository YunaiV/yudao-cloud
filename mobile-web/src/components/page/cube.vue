<template>
<div class="cap-cube" style="font-size:100%;width:100%;">
    <div v-for="(item,index) in data.images" :key="index" class="cap-cube__item" :style="'width:'+item.imgwidth+'rem;'+item.style">
        <a :href="item.link">
            <img :src="item.src+'?w=375'" :style="'width:'+item.imgwidth+'rem;'" />
        </a>
    </div>
    <div style="clear:both;"></div>
</div>
</template>

<script>
export default {
  name: "cube",
  props: {
    data: Object
  },
  created: function() {
    var gap = this.data.imagegap;
    var margin = gap / 2;
    var width = 375;
    var max = 0;
    switch (this.data.type) {
      case "1":
        max = 2;
        width = (width - margin * 2) / 2;
        break;
      case "2":
        max = 3;
        width = (width - margin * 4) / 3;
        break;
      case "3":
        max = 4;
        width = (width - margin * 6) / 4;
        break;
      case "4":
        max = 4;
        width = (width - margin * 2) / 2;
        break;
      case "5":
        max = 3;
        width = (width - margin * 2) / 2;
        break;
      case "6":
        max = 3;
        width = (width - margin * 2) / 2;
        break;
      case "7":
        max = 4;
        width = (width - margin * 2) / 2;
        break;
    }
    margin = margin / 50;
    width = width / 50;
    var imagelist = [];

    for (var i = 0; i < max; i++) {
      var imgwidth = width;
      var item = this.data.imagelist[i];
      var style = "";
      switch (this.data.type) {
        case "1":
          {
            if (i == 0) {
              style = "margin-right:" + margin + "rem;";
            } else {
              style = "margin-left:" + margin + "rem;";
            }
          }
          break;
        case "2":
          {
            if (i == 0) {
              style = "margin-right:" + margin + "rem;";
            } else if (i == 1) {
              style = "margin:0 " + margin + "rem;";
            } else {
              style = "margin-left:" + margin + "rem;";
            }
          }
          break;
        case "3":
          {
            if (i == 0) {
              style = "margin-right:" + margin + "rem;";
            } else if (i == 1 || i == 2) {
              style = "margin:0 " + margin + "rem;";
            } else {
              style = "margin-left:" + margin + "rem;";
            }
          }
          break;
        case "4":
          {
            if (i == 0) {
              style =
                "margin-right:" +
                margin +
                "rem;margin-bottom:" +
                margin +
                "rem;";
            } else if (i == 1) {
              style =
                "margin-left:" +
                margin +
                "rem;margin-bottom:" +
                margin +
                "rem;";
            } else if (i == 2) {
              style =
                "margin-right:" + margin + "rem;margin-top:" + margin + "rem;";
            } else {
              style =
                "margin-left:" + margin + "rem;margin-top:" + margin + "rem;";
            }
          }
          break;
        case "5":
          {
            if (i == 0) {
              style = "margin-right:" + margin + "rem;";
            } else if (i == 1) {
              style =
                "margin-left:" +
                margin +
                "rem;margin-bottom:" +
                margin +
                "rem;";
            } else {
              style =
                "margin-left:" + margin + "rem;margin-top:" + margin + "rem;";
            }
          }
          break;
        case "6":
          {
            if (i == 0) {
              imgwidth = width * 2;
              style = "margin-bottom:" + margin + "rem;";
            } else if (i == 1) {
              style =
                "margin-right:" + margin + "rem;margin-top:" + margin + "rem;";
            } else {
              style =
                "margin-left:" + margin + "rem;margin-top:" + margin + "rem;";
            }
          }
          break;
        case "7":
          {
            if (i == 0) {
              style = "margin-right:" + margin + "rem;";
            } else if (i == 1) {
              style =
                "margin-right:" + margin + "rem;margin-top:" + margin + "rem;";
            } else if (i == 2) {
              imgwidth = width / 2 - margin;
              style =
                "margin-left:" +
                margin +
                "rem;margin-top:" +
                margin +
                "rem;margin-right:" +
                margin +
                "rem";
            } else {
              imgwidth = width / 2 - margin;
              style =
                "margin-left:" + margin + "rem;margin-top:" + margin + "rem;";
            }
          }
          break;
      }
      item.style = style;
      item.imgwidth = imgwidth;
      imagelist.push(item);
    }
    this.data.images = imagelist;
  },
  computed: {}
};
</script>

