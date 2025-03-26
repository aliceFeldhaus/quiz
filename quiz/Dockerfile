# Especifica a imagem base para o container
# Pode ser uma imagem oficial como 'node', 'alpine', etc.
FROM node:18

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Instala as dependências do projeto
RUN npm install

# Expõe a porta que o container usará
EXPOSE 4200

# Comando para iniciar a aplicação, para nao rodar o angular localmente no container e ser possivel acessar de qualquer lugar
# tem que utiliar as configurações conforme abaixo
CMD ["npm", "start", "--", "--host", "0.0.0.0"]
