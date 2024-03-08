<!--
 * @Author: zyw
 * @Date: 2023-12-20 17:36:10
 * @LastEditors: zyw
-->
<script>
import { Message } from 'element-ui'

export default {
  name: "Addxml",
  data() {
    return {
      file: null
    };
  },
  methods: {
    uploadFile(event) {
      this.file = event.target.files[0];
    },
    submitFile() {
      let formData = new FormData();
      formData.append("file", this.file);
      this.request.post("/detailed/addXml", formData)
          .then(response => {
            Message.success("xml导入成功");
            console.log(response);
            this.file=null;
          })
          .catch(error => {
            console.error(error);
          });
    }
  }
}
</script>

<template>
  <div>
    <input type="file" id="xmlFile" accept=".xml" @change="uploadFile" />
    <button @click="submitFile">上传xml文件</button>
  </div>
</template>