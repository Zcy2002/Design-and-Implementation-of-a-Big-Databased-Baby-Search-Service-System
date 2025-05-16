import gradio as gr
import pickle
import pandas as pd
from transformers import AutoModel
from scipy.spatial.distance import cdist
import  numpy as np

with open("data.pkl", "rb") as file:
    data = pickle.load(file)
    embeddings = np.array([item['embedding'] for item in data])

model = AutoModel.from_pretrained('jinaai/jina-embeddings-v2-base-zh', trust_remote_code=True)
file_path = "find_info.xls"
raw_data = pd.read_excel(file_path)
def retrieve_data(query):
    input_embedding = model.encode(query)

    distances = cdist([input_embedding], embeddings, 'cosine')[0]
    k = 10  # 你想要的匹配数量
    closest_indices = np.argsort(distances)[:k]

    # 从原始数据中选取最近的k条记录
    closest_entries = raw_data.iloc[closest_indices]

    # 计算相似度，1减去余弦距离
    similarities = 1 - distances[closest_indices]

    # 将相似度作为新列添加到DataFrame中
    closest_entries['相似度'] = similarities

    return closest_entries

def create_app():
    with gr.Blocks() as demo:

        query = gr.Textbox(label="特征匹配", info="请输入「特征信息」")
        query_button = gr.Button(value="查询")
        result = gr.Dataframe(min_width=500, row_count=10)

        query_button.click(fn=retrieve_data, inputs=[query], outputs=[result])

    return demo


if __name__ == '__main__':
    app = create_app()

    app.launch(server_port=30033,share=True)
