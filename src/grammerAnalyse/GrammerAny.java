package grammerAnalyse;

public interface GrammerAny {
    public void Match(String s);

    public void ReadToken();

    public void Program(GrammerNode node);

    public void ProgramHead(GrammerNode node);

    public void ProgramName(GrammerNode node);

    public void DeclarePart(GrammerNode node);


    public void TypeDecpart(GrammerNode node);

    public void TypeDec(GrammerNode node);

    public void TypeDecList(GrammerNode node);

    public void TypeDecMore(GrammerNode node);

    public void TypeId(GrammerNode node);

    public void TypeDef(GrammerNode node);

    public void BaseType(GrammerNode node);

    public void StructureType(GrammerNode node);

    public void ArrayType(GrammerNode node);


    public void Low(GrammerNode node);

    public void Top(GrammerNode node);

    public void RecType(GrammerNode node);

    public void FieldDecList(GrammerNode node);

    public void FieldDecMore(GrammerNode node);

    public void IdList(GrammerNode node);

    public void IdMore(GrammerNode node);

    public void VarDecpart(GrammerNode node);

    public void VarDec(GrammerNode node);

    public void VarDecList(GrammerNode node);

    public void VarDecMore(GrammerNode node);

    public void VarIdList(GrammerNode node);

    public void VarIdMore(GrammerNode node);

    public void ProcDecpart(GrammerNode node);


    public void ProcDec(GrammerNode node);

    public void ProcDecMore(GrammerNode node);

    public void ProcName(GrammerNode node);

    public void ParamList(GrammerNode node);

    public void ParamDecList(GrammerNode node);

    public void ParamMore(GrammerNode node);

    public void Param(GrammerNode node);

    public void FormList(GrammerNode node);

    public void FidMore(GrammerNode node);

    public void ProcDecPart(GrammerNode node);

    public void ProcBody(GrammerNode node);

    public void ProgramBody(GrammerNode node);

    public void StmList(GrammerNode node);

    public void StmMore(GrammerNode node);

    public void Stm(GrammerNode node);

    public void AssCall(GrammerNode node);

    public void AssignmentRest(GrammerNode node);

    public void ConditionalStm(GrammerNode node);

    public void LoopStm(GrammerNode node);

    public void InputStm(GrammerNode node);

    public void Invar(GrammerNode node);

    public void OutputStm(GrammerNode node);

    public void ReturnStm(GrammerNode node);

    public void CallStmRest(GrammerNode node);

    public void ActParamList(GrammerNode node);

    public void ActParamMore(GrammerNode node);

    public void RelExp(GrammerNode node);

    public void OtherRelE(GrammerNode node);

    public void Exp(GrammerNode node);

    public void OtherTerm(GrammerNode node);

    public void Term(GrammerNode node);

    public void OtherFactor(GrammerNode node);

    public void Factor(GrammerNode node);

    public void Variable(GrammerNode node);

    public void VariMore(GrammerNode node);

    public void FieldVar(GrammerNode node);

    public void FieldVarMore(GrammerNode node);

    public void CmpOp(GrammerNode node);

    public void AddOp(GrammerNode node);

    public void MultOp(GrammerNode node);


}

